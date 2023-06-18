package com.bsep.smart.home.services.logs;

import com.bsep.smart.home.converter.LogConverter;
import com.bsep.smart.home.dto.info.LogPageInfo;
import com.bsep.smart.home.dto.response.LogResponse;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.exception.UnauthorizedException;
import com.bsep.smart.home.jpaspecification.PagingUtil;
import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import com.bsep.smart.home.mongorepository.LogRepository;
import com.bsep.smart.home.repository.PropertyRepository;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SearchLogs {
    private final LogRepository logRepository;
    private final PropertyRepository propertyRepository;
    private final GetLoggedInUser getLoggedInUser;

    @Transactional(readOnly = true)
    public PageResponse<LogResponse> execute(LogPageInfo logPageInfo) {
        Property property = propertyRepository.findById(logPageInfo.getPropertyId()).get();
        Person loggedUser = getLoggedInUser.execute();
        if (!property.getMembers().contains(loggedUser) && !loggedUser.getRole().getName().equals("ADMIN")) {
            throw new UnauthorizedException();
        }
        final Pageable pageable = PagingUtil.getPageable(logPageInfo.getPage(), logPageInfo.getSize(), Optional.of(Sort.by(Sort.Direction.DESC, "createdAt")));
        Page<Log> logPage = logRepository.searchLogsByRegexAndPropertyIdAndProcessed(Pattern.compile(logPageInfo.getSearch()), String.valueOf(logPageInfo.getPropertyId()), pageable);
        List<LogResponse> logResponses = LogConverter.toLogResponseList(logPage.getContent());
        return PageResponse.<LogResponse>builder()
                .pageNumber(logPageInfo.getPage())
                .pageSize(logPageInfo.getSize())
                .items(logResponses)
                .totalPages(logPage.getTotalPages())
                .numberOfElements(logPage.getNumberOfElements())
                .totalElements(logPage.getTotalElements())
                .build();
    }
}
