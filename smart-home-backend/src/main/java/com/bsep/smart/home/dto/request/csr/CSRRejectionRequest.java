package com.bsep.smart.home.dto.request.csr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CSRRejectionRequest {
    @NotBlank
    UUID id;
    @NotBlank
    String reason;
}
