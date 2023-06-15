package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.response.DeviceRuleResponse;
import com.bsep.smart.home.model.DeviceRule;

public class DeviceRuleConverter {
    public static DeviceRuleResponse toDeviceRuleResponse(final DeviceRule deviceRule) {
        return DeviceRuleResponse.builder()
                .maxValue(deviceRule.getMaxValue())
                .minValue(deviceRule.getMinValue())
                .build();
    }
}
