package com.bsep.smart.home.jpaspecification;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PagingUtil {
    public static Pageable getPageable(final int page, final int size) {
        return PageRequest.of(page, size);
    }
}
