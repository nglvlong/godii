package com.fpt.godii.application.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO<T> {
    private List<T> items;
    private Long totalItem;
    private Long otherTabCount;

    public PaginationDTO(List<T> items, Long totalItem) {
        this.items = items;
        this.totalItem = totalItem;
    }

    public PaginationDTO(Page<T> pageResults) {
        this.items = pageResults.getContent();
        this.totalItem = pageResults.getTotalElements();
    }
}
