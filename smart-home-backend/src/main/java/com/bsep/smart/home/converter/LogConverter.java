package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.info.LogPageInfo;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class LogConverter {
    public static LogPageInfo toLogPageInfo(final int page, final int size, final String search) {
        return LogPageInfo.builder().page(page)
                .size(size)
                .search(Objects.requireNonNullElse(search, Strings.EMPTY))
                .build();
    }
}
