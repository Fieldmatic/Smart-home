package com.bsep.smart.home.jpaspecification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SearchRequest {
    private List<FilterRequest> filters;

    private SortRequest sort;

    private int page;

    private int size;
}
