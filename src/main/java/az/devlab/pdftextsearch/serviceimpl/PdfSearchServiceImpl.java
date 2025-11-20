package az.devlab.pdftextsearch.serviceimpl;

import az.devlab.pdftextsearch.dto.PagedSearchResponse;
import az.devlab.pdftextsearch.dto.SearchRequest;
import az.devlab.pdftextsearch.dto.SearchResultItemResponse;
import az.devlab.pdftextsearch.mapper.SearchResultMapper;
import az.devlab.pdftextsearch.model.PdfDocumentIndex;
import az.devlab.pdftextsearch.repository.PdfDocumentSearchRepository;
import az.devlab.pdftextsearch.sercive.PdfSearchService;
import az.devlab.pdftextsearch.util.SearchUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfSearchServiceImpl implements PdfSearchService {

    private final PdfDocumentSearchRepository pdfDocumentSearchRepository;
    private final SearchResultMapper searchResultMapper;

    @Override
    public PagedSearchResponse<SearchResultItemResponse> search(SearchRequest request) {
        String query = request.getQuery();
        Pageable pageable = SearchUtils.toPageable(request);

        Page<PdfDocumentIndex> page;
        if (query == null || query.isBlank()) {
            log.info("Executing search without query (match all), page={}, size={}",
                    pageable.getPageNumber(), pageable.getPageSize());
            page = pdfDocumentSearchRepository.findAll(pageable);
        } else {
            log.info("Executing search for query='{}', page={}, size={}",
                    query, pageable.getPageNumber(), pageable.getPageSize());
            page = pdfDocumentSearchRepository.findByContentContaining(query, pageable);
        }

        List<SearchResultItemResponse> items = page.getContent()
                .stream()
                .map(searchResultMapper::toResponse)
                .toList();

        return PagedSearchResponse.<SearchResultItemResponse>builder()
                .content(items)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
