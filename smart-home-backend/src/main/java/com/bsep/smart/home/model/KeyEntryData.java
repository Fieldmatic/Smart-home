package com.bsep.smart.home.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.security.auth.x500.X500Principal;
import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyEntryData {
    private X500Principal x500Principal;
    private PrivateKey privateKey;
    private PublicKey publicKey;
}
