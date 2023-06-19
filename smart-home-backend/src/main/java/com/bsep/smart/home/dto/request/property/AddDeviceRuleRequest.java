package com.bsep.smart.home.dto.request.property;

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
public class AddDeviceRuleRequest {
    @Pattern(regexp = UUID_PATTERN, message = "Bad uuid format!")
    private String deviceId;
    @NotNull(message = "Minimum value is missing")
    private double minValue;
    @NotNull(message = "Maximum value is missing")
    private double maxValue;
}
