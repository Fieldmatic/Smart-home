package com.bsep.smart.home.services.message;

import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.jpaspecification.PagingUtil;
import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.mongorepository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchMessages {
    private final LogRepository logRepository;

    @Transactional(readOnly = true)
    public PageResponse<String> execute(String propertyId, String filter, int pageNumber, int pageSize) {
        Pageable pageable = PagingUtil.getPageable(pageNumber, pageSize, Optional.empty());
        Page<Log> logPage;
        if (!Objects.equals(filter, "") && !Objects.equals(filter, " ")) {
            List<String> filters = Arrays.stream(filter.split(" ")).distinct().collect(Collectors.toList());
            filters.remove("");
            String regex = "(?=.*" + String.join(")(?=.*", filters) + ")";
            System.out.println(regex);
            Pattern filterPattern = Pattern.compile(regex);
            logPage = logRepository.getLogsByPropertyIdAndMessageContainsFilters(propertyId, filterPattern, pageable);
        } else {
            logPage = logRepository.getLogsByPropertyId(propertyId, pageable);
        }
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
