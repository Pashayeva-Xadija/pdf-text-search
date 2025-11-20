package az.devlab.pdftextsearch.repository;

import az.devlab.pdftextsearch.model.PdfDocumentIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfDocumentSearchRepository extends ElasticsearchRepository<PdfDocumentIndex, String> {

    Page<PdfDocumentIndex> findByContentContaining(String query, Pageable pageable);
}
