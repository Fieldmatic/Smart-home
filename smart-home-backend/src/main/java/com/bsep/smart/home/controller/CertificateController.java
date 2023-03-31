package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.certificate.CreateCertificateRequest;
import com.bsep.smart.home.dto.response.CertificateResponse;
import com.bsep.smart.home.model.CertificateType;
import com.bsep.smart.home.model.Extension;
import com.bsep.smart.home.services.certificate.CreateCertificate;
import com.bsep.smart.home.services.certificate.GetAllCertificates;
import com.bsep.smart.home.services.certificate.GetCertificateExtensions;
import com.bsep.smart.home.services.certificate.GetCertificateTypes;
import com.bsep.smart.home.services.keystore.DeleteEntryByAlias;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {

    private final CreateCertificate generateCertificate;
    private final DeleteEntryByAlias deleteEntryByAlias;
    private final GetCertificateTypes getCertificateTypes;
    private final GetCertificateExtensions getCertificateExtensions;
    private final GetAllCertificates getAllCertificates;

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

    @GetMapping("/types")
    // @HasAnyPermission({Permission.CERTIFICATE_MANIPULATION})
    public List<CertificateType> getCertificateTypes() {
        return getCertificateTypes.execute();
    }

    @GetMapping("/extensions")
    // @HasAnyPermission({Permission.CERTIFICATE_MANIPULATION})
    public List<Extension> getExtensions() {
        return getCertificateExtensions.execute();
    }

    @GetMapping("/all")
    // @HasAnyPermission({Permission.CERTIFICATE_MANIPULATION})
    public List<CertificateResponse> getCertificates() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertPathValidatorException, InvalidAlgorithmParameterException, CertificateException {
        return getAllCertificates.execute();
    }
}
