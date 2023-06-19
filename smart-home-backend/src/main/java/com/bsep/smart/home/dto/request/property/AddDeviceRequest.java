package com.bsep.smart.home.dto.request.property;

import com.bsep.smart.home.model.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.bsep.smart.home.util.ValidationPatterns.UUID_PATTERN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddDeviceRequest {
    @NotEmpty(message = "Device name is missing")
    private String name;
    @Pattern(regexp = UUID_PATTERN, message = "Bad uuid format!")
    private String propertyId;
    @NotNull(message = "Device type is missing")
    private DeviceType deviceType;
    @NotNull(message = "Read period is missing")
    private Long readPeriod;
    @NotEmpty(message = "Message regex is missing")
    private String messageRegex;
}
