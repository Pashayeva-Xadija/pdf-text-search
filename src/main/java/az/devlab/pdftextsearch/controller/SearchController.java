package az.devlab.pdftextsearch.controller;

import az.devlab.pdftextsearch.dto.PagedSearchResponse;
import az.devlab.pdftextsearch.dto.SearchRequest;
import az.devlab.pdftextsearch.dto.SearchResultItemResponse;
import az.devlab.pdftextsearch.sercive.PdfSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final PdfSearchService pdfSearchService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedSearchResponse<SearchResultItemResponse> search( @RequestBody @Valid SearchRequest request) {
        log.info("Search request received. query='{}', page={}, size={}",
                request.getQuery(), request.getPage(), request.getSize());

        return pdfSearchService.search(request);
    }
}
