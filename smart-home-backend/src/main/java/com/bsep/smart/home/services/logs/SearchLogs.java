package com.bsep.smart.home.services.logs;

import com.bsep.smart.home.dto.info.LogPageInfo;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SearchLogs {
    private final LogRepository logRepository;
    private final PropertyRepository propertyRepository;
    private final GetLoggedInUser getLoggedInUser;

    @Transactional(readOnly = true)
    public PageResponse<Log> execute(LogPageInfo logPageInfo) {
        Property property = propertyRepository.findById(logPageInfo.getPropertyId()).get();
        Person loggedUser = getLoggedInUser.execute();
        if (!property.getMembers().contains(loggedUser) && !loggedUser.getRole().getName().equals("ADMIN")) {
            throw new UnauthorizedException();
        }
        final Pageable pageable = PagingUtil.getPageable(logPageInfo.getPage(), logPageInfo.getSize());
        Page<Log> logPage = logRepository.searchLogsByRegexAndPropertyId(Pattern.compile(logPageInfo.getSearch()), String.valueOf(logPageInfo.getPropertyId()), pageable);
        return PageResponse.<Log>builder()
                .pageNumber(logPageInfo.getPage())
                .pageSize(logPageInfo.getSize())
                .items(new ArrayList<>(logPage.getContent()))
                .totalPages(logPage.getTotalPages())
                .numberOfElements(logPage.getNumberOfElements())
                .totalElements(logPage.getTotalElements())
                .build();
    }
}
