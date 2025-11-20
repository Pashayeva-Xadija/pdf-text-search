package az.devlab.pdftextsearch.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfUploadResponse {

    private Long documentId;
    private String fileName;
    private long fileSize;
    private boolean indexed;
    private LocalDateTime uploadedAt;
    private String message;
}
