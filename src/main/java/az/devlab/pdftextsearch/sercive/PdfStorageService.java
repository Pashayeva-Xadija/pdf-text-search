package az.devlab.pdftextsearch.sercive;

import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PdfStorageService {

    PdfDocumentEntity store(MultipartFile file);
}
