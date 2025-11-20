package az.devlab.pdftextsearch.sercive;

import az.devlab.pdftextsearch.dto.PagedSearchResponse;
import az.devlab.pdftextsearch.dto.SearchRequest;
import az.devlab.pdftextsearch.dto.SearchResultItemResponse;


public interface PdfSearchService {

    PagedSearchResponse<SearchResultItemResponse> search(SearchRequest request);
}
