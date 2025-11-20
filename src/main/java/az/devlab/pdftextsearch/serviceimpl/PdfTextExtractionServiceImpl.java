package az.devlab.pdftextsearch.serviceimpl;

import az.devlab.pdftextsearch.exception.IndexingException;
import az.devlab.pdftextsearch.sercive.PdfTextExtractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfTextExtractionServiceImpl implements PdfTextExtractionService {

    private final Tika tika;

    @Override
    public String extractText(Path filePath) {
        try {
            log.info("Extracting text from PDF: {}", filePath);
            return tika.parseToString(Files.newInputStream(filePath));
        } catch (Exception ex) {
            log.error("Failed to extract text from file: {}", filePath, ex);
            throw new IndexingException("Failed to extract text from PDF: " + filePath, ex);
        }
    }
}
