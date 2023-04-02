package com.bsep.smart.home.dto.request.csr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CSRRequest {
    @NotEmpty
    String commonName;
    String organization;
    String organizationalUnit;
    String city;
    String state;
    String country;
    @NotNull
    int keySize;
    @NotEmpty
    String algorithm;
}
