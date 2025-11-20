package az.devlab.pdftextsearch.serviceimpl;

import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import az.devlab.pdftextsearch.sercive.PdfIndexService;
import az.devlab.pdftextsearch.sercive.PdfProcessingService;
import az.devlab.pdftextsearch.sercive.PdfTextExtractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfProcessingServiceImpl implements PdfProcessingService {

    private final PdfTextExtractionService pdfTextExtractionService;
    private final PdfIndexService pdfIndexService;

    @Override
    @Async("pdfTaskExecutor")
    public void processPdf(PdfDocumentEntity documentEntity) {
        try {
            log.info("Starting async processing for PDF documentId={}", documentEntity.getId());

            Path filePath = Paths.get(documentEntity.getStoragePath());
            String content = pdfTextExtractionService.extractText(filePath);

            pdfIndexService.indexDocument(documentEntity, content);

            log.info("Finished async processing (extract + index) for documentId={}", documentEntity.getId());
        } catch (Exception ex) {
            log.error("Error during async processing for documentId={}", documentEntity.getId(), ex);
        }
    }
}
