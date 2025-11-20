package az.devlab.pdftextsearch.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultItemResponse {

    private String id;
    private Long documentId;
    private String fileName;
    private String snippet;
    private long fileSize;
    private LocalDateTime uploadedAt;

}
