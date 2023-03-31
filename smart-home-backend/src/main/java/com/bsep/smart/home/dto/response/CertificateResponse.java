package com.bsep.smart.home.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateResponse {
    String email;
    String issuedFor;
    String issuedBy;
    Date start;
    Date end;
    boolean isValid;

}
