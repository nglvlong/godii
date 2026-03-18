package com.fpt.godii.application.base.request;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.fpt.godii.application.utils.constants.Constants.COMMON.SORT_ASC;
import static com.fpt.godii.application.utils.constants.Constants.COMMON.STRING_EMPTY;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationParams {
    @Getter(AccessLevel.NONE)
    private int pageNumber = 1;
    @Getter(AccessLevel.NONE)
    private int pageSize = 5;
    private String fieldSort = STRING_EMPTY;
    private String sortType = SORT_ASC;

    public Pageable toPage() {
        if (fieldSort.equals(STRING_EMPTY))
            return PageRequest.of(this.pageNumber - 1, pageSize);
        else if (sortType.equals(SORT_ASC))
            return PageRequest.of(this.pageNumber - 1, pageSize, Sort.by(fieldSort).ascending());
        else
            return PageRequest.of(this.pageNumber - 1, pageSize, Sort.by(fieldSort).descending());
    }

    public int getPageSize() {
        return Math.max(pageSize, 1);
    }

    public int getPageNumber() {
        return Math.max(pageNumber, 1);
    }
}
