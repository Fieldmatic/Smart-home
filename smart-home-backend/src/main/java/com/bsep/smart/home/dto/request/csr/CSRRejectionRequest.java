package com.bsep.smart.home.dto.request.csr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CSRRejectionRequest {
    UUID id;
    String reason;
}
