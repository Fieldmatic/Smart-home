package com.bsep.smart.home.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceRuleResponse {
    double maxValue;
    double minValue;
}
