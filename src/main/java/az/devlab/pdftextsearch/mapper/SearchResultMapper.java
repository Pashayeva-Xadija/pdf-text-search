package az.devlab.pdftextsearch.mapper;

import az.devlab.pdftextsearch.dto.SearchResultItemResponse;
import az.devlab.pdftextsearch.model.PdfDocumentIndex;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SearchResultMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "documentId", source = "documentId")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "fileSize", source = "fileSize")
    @Mapping(target = "uploadedAt", source = "uploadedAt")
    @Mapping(target = "snippet", expression = "java(createSnippet(index.getContent()))")
    SearchResultItemResponse toResponse(PdfDocumentIndex index);

    default String createSnippet(String content) {
        if (content == null) {
            return null;
        }
        int maxLength = 200;
        return content.length() > maxLength
                ? content.substring(0, maxLength) + "..."
                : content;
    }
}
