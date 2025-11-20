package az.devlab.pdftextsearch.repository;

import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfDocumentJpaRepository extends JpaRepository<PdfDocumentEntity, Long> {
}
