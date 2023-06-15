package com.bsep.smart.home.dto.response;

import com.bsep.smart.home.model.DeviceType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponse {
    UUID uuid;
    String name;
    DeviceType deviceType;
    String messageRegex;
    Long readPeriod;
    List<AlarmResponse> alarms;
    DeviceRuleResponse deviceRule;
}
