package com.bsep.smart.home.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceRuleResponse {
    double minValue;
    double maxValue;
}
