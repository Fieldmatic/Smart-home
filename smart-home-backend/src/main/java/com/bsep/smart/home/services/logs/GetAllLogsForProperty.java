package com.bsep.smart.home.services.logs;

import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.exception.UnauthorizedException;
import com.bsep.smart.home.jpaspecification.PagingUtil;
import com.bsep.smart.home.model.Log;
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

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllLogsForProperty {
    private final LogRepository logRepository;
    private final PropertyRepository propertyRepository;
    private final GetLoggedInUser getLoggedInUser;

    @Transactional
    public PageResponse<Log> execute(String propertyId, int pageNumber, int pageSize) {
        Property property = propertyRepository.findById(UUID.fromString(propertyId)).get();
        if (!property.getMembers().contains(getLoggedInUser.execute()) && !getLoggedInUser.execute().getRole().getName().equals("ADMIN")) {
            throw new UnauthorizedException();
        }
        final Pageable pageable = PagingUtil.getPageable(pageNumber, pageSize, Optional.of(Sort.by(Sort.Direction.DESC, "createdAt")));
        final Page<Log> logPage = logRepository.getLogsByPropertyId(propertyId, pageable);
        return PageResponse.<Log>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .items(new ArrayList<>(logPage.getContent()))
                .totalPages(logPage.getTotalPages())
                .numberOfElements(logPage.getNumberOfElements())
                .totalElements(logPage.getTotalElements())
                .build();
    }
}
