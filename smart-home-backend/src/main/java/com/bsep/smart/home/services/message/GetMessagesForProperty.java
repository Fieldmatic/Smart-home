package com.bsep.smart.home.services.message;


import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.jpaspecification.PagingUtil;
import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.mongorepository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMessagesForProperty {
    private final LogRepository logRepository;

    @Transactional(readOnly = true)
    public PageResponse<String> execute(String propertyId, int pageNumber, int pageSize) {
        Pageable pageable = PagingUtil.getPageable(pageNumber, pageSize, Optional.of(Sort.by(Sort.Direction.DESC, "createdAt")));
        Page<Log> logPage = logRepository.getLogsByPropertyId(propertyId, pageable);
        return PageResponse.<String>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .items(logPage.getContent().stream().map(Log::getMessage).collect(Collectors.toList()))
                .totalPages(logPage.getTotalPages())
                .numberOfElements(logPage.getNumberOfElements())
                .totalElements(logPage.getTotalElements())
                .build();
    }
}
