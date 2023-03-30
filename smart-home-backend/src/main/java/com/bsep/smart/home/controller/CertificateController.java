package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.certificate.CreateCertificateRequest;
import com.bsep.smart.home.services.certificate.CreateCertificate;
import com.bsep.smart.home.services.keystore.DeleteEntryByAlias;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {

    private final CreateCertificate generateCertificate;
    private final DeleteEntryByAlias deleteEntryByAlias;

    @PostMapping
    // @HasAnyPermission({Permission.CERTIFICATE_MANIPULATION})
    public void create(@RequestBody CreateCertificateRequest createCertificateRequest) throws UnrecoverableKeyException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, OperatorCreationException, InvalidKeyException, NoSuchProviderException, IOException {
        generateCertificate.execute(createCertificateRequest);
    }

    @DeleteMapping("/delete/{alias}")
    // @HasAnyPermission({Permission.CERTIFICATE_MANIPULATION})
    public void delete(@PathVariable String alias) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        deleteEntryByAlias.execute(alias);
    }
}
