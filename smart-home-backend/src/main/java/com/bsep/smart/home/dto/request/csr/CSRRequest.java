package com.bsep.smart.home.dto.request.csr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CSRRequest {
    String commonName;
    String organization;
    String organizationalUnit;
    String city;
    String state;
    String country;
    int keySize;
    String algorithm;
}
