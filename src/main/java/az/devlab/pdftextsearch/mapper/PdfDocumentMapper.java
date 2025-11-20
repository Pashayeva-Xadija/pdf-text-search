package az.devlab.pdftextsearch.mapper;

import az.devlab.pdftextsearch.dto.PdfUploadResponse;
import az.devlab.pdftextsearch.model.PdfDocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PdfDocumentMapper {

    @Mapping(target = "documentId", source = "id")
    @Mapping(target = "indexed", ignore = true)
    @Mapping(target = "message", ignore = true)
    PdfUploadResponse toUploadResponse(PdfDocumentEntity entity);
}
