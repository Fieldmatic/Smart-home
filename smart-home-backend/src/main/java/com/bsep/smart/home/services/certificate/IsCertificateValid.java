package com.bsep.smart.home.services.certificate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class IsCertificateValid {

    public boolean execute(X509Certificate certificate) {
        try {
            certificate.checkValidity();
            verifySignature(certificate);
            return true;
        } catch (CertificateNotYetValidException | CertificateExpiredException | InvalidKeyException |
                 CertificateEncodingException | NoSuchAlgorithmException | SignatureException e) {
            return false;
        }
    }

    private void verifySignature(X509Certificate certificate) throws InvalidKeyException, CertificateEncodingException, NoSuchAlgorithmException, SignatureException {
        PublicKey publicKey = certificate.getPublicKey();
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(certificate.getTBSCertificate());
        signature.verify(certificate.getSignature());
    }
}
