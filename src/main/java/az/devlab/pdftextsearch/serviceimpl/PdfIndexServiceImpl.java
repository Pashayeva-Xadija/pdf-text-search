package az.devlab.pdftextsearch.serviceimpl;

import az.devlab.pdftextsearch.exception.IndexingException;
import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import az.devlab.pdftextsearch.model.PdfDocumentIndex;
import az.devlab.pdftextsearch.repository.PdfDocumentSearchRepository;
import az.devlab.pdftextsearch.sercive.PdfIndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfIndexServiceImpl implements PdfIndexService {

    private final PdfDocumentSearchRepository pdfDocumentSearchRepository;

    @Override
    public PdfDocumentIndex indexDocument(PdfDocumentEntity documentEntity, String content) {
        try {
            PdfDocumentIndex index = PdfDocumentIndex.builder()
                    .documentId(documentEntity.getId())
                    .fileName(documentEntity.getFileName())
                    .content(content)
                    .contentType(documentEntity.getContentType())
                    .fileSize(documentEntity.getFileSize())
                    .uploadedAt(documentEntity.getCreatedAt())
                    .build();

            PdfDocumentIndex saved = pdfDocumentSearchRepository.save(index);
            log.info("Indexed PDF document into Elasticsearch. documentId={}, esId={}",
                    documentEntity.getId(), saved.getId());
            return saved;
        } catch (Exception ex) {
            log.error("Failed to index PDF document with id={}", documentEntity.getId(), ex);
            throw new IndexingException("Failed to index PDF document with id=" + documentEntity.getId(), ex);
        }
    }
}
