package com.bsep.smart.home.dto.response;

import com.bsep.smart.home.model.DeviceType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponse {
    String name;
    DeviceType deviceType;
    String messageRegex;
    Long readPeriod;
    List<AlarmResponse> alarms;
    DeviceRuleResponse deviceRule;
}
