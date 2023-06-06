package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.info.LogPageInfo;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.UUID;

public class LogConverter {
    public static LogPageInfo toLogPageInfo(final int page, final int size, final String search, final UUID propertyId) {
        return LogPageInfo.builder().page(page)
                .size(size)
                .search(Objects.requireNonNullElse(search, Strings.EMPTY))
                .propertyId(propertyId)
                .build();
    }
}
