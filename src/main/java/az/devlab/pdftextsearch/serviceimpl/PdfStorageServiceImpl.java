package az.devlab.pdftextsearch.serviceimpl;

import az.devlab.pdftextsearch.config.StorageProperties;
import az.devlab.pdftextsearch.exception.FileStorageException;
import az.devlab.pdftextsearch.model.DocumentStatus;
import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import az.devlab.pdftextsearch.repository.PdfDocumentJpaRepository;
import az.devlab.pdftextsearch.sercive.PdfStorageService;
import az.devlab.pdftextsearch.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfStorageServiceImpl implements PdfStorageService {

    private final StorageProperties storageProperties;
    private final PdfDocumentJpaRepository pdfDocumentJpaRepository;

    @Override
    public PdfDocumentEntity store(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileStorageException("Uploaded file is empty");
        }

        if (file.getContentType() == null || !file.getContentType().equalsIgnoreCase("application/pdf")) {
            throw new FileStorageException("Only PDF files are allowed");
        }


        String normalizedFileName = FileUtils.normalizeFileName(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID() + "_" + normalizedFileName;

        try {
            Path uploadDir = Paths.get(storageProperties.getUploadDir())
                    .toAbsolutePath()
                    .normalize();

            Files.createDirectories(uploadDir);

            Path targetLocation = FileUtils.buildStoragePath(uploadDir.toString(), uniqueFileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            PdfDocumentEntity entity = PdfDocumentEntity.builder()
                    .fileName(normalizedFileName)
                    .storagePath(targetLocation.toString())
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())
                    .createdAt(LocalDateTime.now())
                    .status(DocumentStatus.UPLOADED)
                    .build();


            PdfDocumentEntity saved = pdfDocumentJpaRepository.save(entity);
            log.info("Stored PDF file: {} at path: {}", saved.getFileName(), saved.getStoragePath());

            return saved;
        } catch (IOException ex) {
            log.error("Failed to store file {}", normalizedFileName, ex);
            throw new FileStorageException("Could not store file: " + normalizedFileName, ex);
        }
    }
}
