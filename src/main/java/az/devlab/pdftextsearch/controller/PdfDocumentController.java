package az.devlab.pdftextsearch.controller;

import az.devlab.pdftextsearch.dto.PdfUploadRequest;
import az.devlab.pdftextsearch.dto.PdfUploadResponse;
import az.devlab.pdftextsearch.mapper.PdfDocumentMapper;
import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import az.devlab.pdftextsearch.sercive.PdfProcessingService;
import az.devlab.pdftextsearch.sercive.PdfStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pdfs")
public class PdfDocumentController {

    private final PdfStorageService pdfStorageService;
    private final PdfProcessingService pdfProcessingService;
    private final PdfDocumentMapper pdfDocumentMapper;


    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PdfUploadResponse> uploadPdf(@RequestPart("file") MultipartFile file, @RequestPart(value = "meta", required = false) @Valid PdfUploadRequest meta
    ) {
        log.info("Received PDF upload request. fileName={}", file.getOriginalFilename());

        PdfDocumentEntity entity = pdfStorageService.store(file);

        pdfProcessingService.processPdf(entity);

        PdfUploadResponse response = pdfDocumentMapper.toUploadResponse(entity);
        response.setIndexed(false);
        response.setUploadedAt(entity.getCreatedAt());
        response.setMessage("PDF upload olundu, indexləşdirmə async olaraq davam edir.");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
