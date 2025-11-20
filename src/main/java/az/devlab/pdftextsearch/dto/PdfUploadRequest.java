package az.devlab.pdftextsearch.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfUploadRequest {

    private String title;
    private String description;
}
