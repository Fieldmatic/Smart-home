package com.bsep.smart.home.services.logs;

import com.bsep.smart.home.dto.info.LogPageInfo;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.jpaspecification.PagingUtil;
import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.mongorepository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SearchLogs {
    private final LogRepository logRepository;

    @Transactional(readOnly = true)
    public PageResponse<Log> execute(LogPageInfo logPageInfo) {
        final Pageable pageable = PagingUtil.getPageable(logPageInfo.getPage(), logPageInfo.getSize());
        Page<Log> logPage = logRepository.searchLogsByRegex(replaceReservedCharacters(logPageInfo.getSearch()), pageable);
        return PageResponse.<Log>builder()
                .pageNumber(logPageInfo.getPage())
                .pageSize(logPageInfo.getSize())
                .items(new ArrayList<>(logPage.getContent()))
                .totalPages(logPage.getTotalPages())
                .numberOfElements(logPage.getNumberOfElements())
                .totalElements(logPage.getTotalElements())
                .build();
    }

    public String replaceReservedCharacters(String input) {
        String[] reservedCharacters = {"$", ".", "{", "}", "[", "]", "(", ")", "<", ">", "=", "!", "&", "|", "?", "*", "+", "^", "~", "-"};

        for (String character : reservedCharacters) {
            input = input.replace(character, "\\\\" + character);
        }

        return input;
    }
}
