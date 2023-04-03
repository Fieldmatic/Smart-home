package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.services.keystore.LoadX509Certificate;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IsCertificateRevoked {
    private final ResourceLoader resourceLoader;
    private final LoadX509Certificate loadX509Certificate;

    public boolean execute(String alias) throws CertificateException, IOException, CRLException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509Certificate certificate = loadX509Certificate.execute(alias);
        InputStream crlStream = resourceLoader.getResource("classpath:keystore/crl.crl").getInputStream();
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509CRL crl = (X509CRL) cf.generateCRL(crlStream);
        if (!Objects.isNull(crl.getRevokedCertificates())) {
            for (X509CRLEntry entry : crl.getRevokedCertificates()) {
                SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(certificate.getPublicKey().getEncoded());
                JcaX509ExtensionUtils extensionUtils = new JcaX509ExtensionUtils();
                byte[] certSubjectKeyIdentifier = extensionUtils.createSubjectKeyIdentifier(publicKeyInfo).getEncoded();
                byte[] crlSubjectKeyIdentifier = entry.getExtensionValue(org.bouncycastle.asn1.x509.Extension.subjectKeyIdentifier.getId());
                Extension certCrtExtension = new Extension(Extension.subjectKeyIdentifier, false, certSubjectKeyIdentifier);
                if (Arrays.equals(crlSubjectKeyIdentifier, certCrtExtension.getExtnValue().getEncoded()) && entry.getSerialNumber().longValue() == certificate.getSerialNumber().longValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
