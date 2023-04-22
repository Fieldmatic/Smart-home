package com.bsep.smart.home.dto.request.certificate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevokeRequest {
    @NotBlank
    String alias;
    String message;
    @NotNull
    int reason;
}
