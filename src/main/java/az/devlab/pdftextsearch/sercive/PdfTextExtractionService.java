package az.devlab.pdftextsearch.sercive;

import java.nio.file.Path;

public interface PdfTextExtractionService {

    String extractText(Path filePath);
}
