package az.devlab.pdftextsearch.util;

import az.devlab.pdftextsearch.dto.SearchRequest;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class SearchUtils {

    public Pageable toPageable(SearchRequest request) {
        int page = request.getPage() < 0 ? 0 : request.getPage();
        int size = request.getSize() <= 0 ? 10 : request.getSize();

        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "uploadedAt"));
    }
}
