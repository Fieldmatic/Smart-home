package com.bsep.smart.home.dto.request.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.bsep.smart.home.util.ValidationPatterns.UUID_PATTERN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePropertyRequest {
    @NotEmpty(message = "Property name is missing")
    private String name;
    @NotEmpty(message = "Property address is missing")
    private String address;
    @Pattern(regexp = UUID_PATTERN, message = "Bad uuid format!")
    private String ownerId;
}
