package com.bsep.smart.home.dto.info;

import com.bsep.smart.home.model.Log;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LogPageInfo {
    public final static String[] SEARCH_FIELDS = {Log.Fields.message, Log.Fields.createdAt, Log.Fields.deviceId, Log.Fields.propertyId};

    private int page;

    private int size;

    private String search;

    private UUID propertyId;

}
