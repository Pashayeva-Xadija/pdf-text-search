package az.devlab.pdftextsearch.sercive;

import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import az.devlab.pdftextsearch.model.PdfDocumentIndex;

public interface PdfIndexService {

    PdfDocumentIndex indexDocument(PdfDocumentEntity documentEntity, String content);
}
