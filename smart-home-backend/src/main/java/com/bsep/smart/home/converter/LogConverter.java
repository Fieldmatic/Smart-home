package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.info.LogPageInfo;
import com.bsep.smart.home.dto.response.LogResponse;
import com.bsep.smart.home.model.Log;
import org.apache.logging.log4j.util.Strings;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class LogConverter {
    public static LogPageInfo toLogPageInfo(final int page, final int size, final String search, final UUID propertyId) {
        return LogPageInfo.builder().page(page)
                .size(size)
                .search(Objects.requireNonNullElse(search, Strings.EMPTY))
                .propertyId(propertyId)
                .build();
    }

    public static List<LogResponse> toLogResponseList(List<Log> logs) {
        if (Objects.isNull(logs)) return Collections.emptyList();
        return logs.stream().map(log -> LogResponse.builder()
                        .id(log.getId())
                        .message(log.getMessage())
                        .createdAt(log.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
