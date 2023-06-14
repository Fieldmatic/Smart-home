package com.bsep.smart.home.jpaspecification;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class PagingUtil {
    public static Pageable getPageable(final int page, final int size, Optional<Sort> sort) {
        return sort.map(sortDirection -> PageRequest.of(page, size, sortDirection)).orElseGet(() -> PageRequest.of(page, size));
    }
}
