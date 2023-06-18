package com.bsep.smart.home.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.security.auth.x500.X500Principal;
import java.security.PublicKey;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectData {
    private PublicKey publicKey;
    private X500Principal x500Principal;
    private String serialNumber;
    private Date startDate;
    private Date endDate;
}
