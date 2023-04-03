package com.bsep.smart.home.services.certificate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CRLException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class CheckCertificateValidity {
    private final IsCertificateValid isCertificateValid;
    private final IsCertificateRevoked isCertificateRevoked;
    private final IsCertificateChainValid isCertificateChainValid;

    public boolean execute(String alias) throws CertPathValidatorException, InvalidAlgorithmParameterException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, IOException, CRLException, InvalidKeySpecException {
        boolean certificateValid = isCertificateValid.execute(alias);
        boolean certificateChainValid = isCertificateChainValid.execute(alias);
        boolean certificateRevoked = isCertificateRevoked.execute(alias);
        return certificateValid & certificateChainValid && !certificateRevoked;
    }
}
