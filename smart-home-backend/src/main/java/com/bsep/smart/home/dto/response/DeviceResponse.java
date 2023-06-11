package com.bsep.smart.home.dto.response;

import com.bsep.smart.home.model.DeviceType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponse {
    String name;
    DeviceType deviceType;
    String messageRegex;
}
