package az.devlab.pdftextsearch.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    @NotBlank(message = "query boş ola bilməz")
    private String query;

    @Min(value = 0, message = "Page 0-dan kiçik ola bilməz")
    private int page;

    @Min(value = 1, message = "Size 1-dən kiçik ola bilməz")
    @Max(value = 100, message = "Size 100-dən böyük ola bilməz")
    private int size;
}
