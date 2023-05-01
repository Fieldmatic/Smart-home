package com.bsep.smart.home.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageResponse<T> {
    private List<T> items;

    private int totalPages;

    private int pageSize;

    private int numberOfElements;

    private int pageNumber;

    public boolean hasNextPage() {
        return pageNumber < totalPages;
    }

    public boolean hasPreviousPage() {
        return pageNumber > 0;
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }
}
