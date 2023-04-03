package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.dto.request.certificate.CapabilityRequest;
import com.bsep.smart.home.model.KeyEntryData;
import com.bsep.smart.home.model.SubjectData;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateGenerator {
    private final AddExtensions addExtensions;

    public X509Certificate execute(SubjectData subjectData, KeyEntryData issuerData, List<CapabilityRequest> capabilities, String algorithm) throws CertificateException, OperatorCreationException, CertIOException {
        ContentSigner contentSigner = getContentSigner(issuerData.getPrivateKey(), algorithm);
        X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
                issuerData.getX500Principal(),
                new BigInteger(subjectData.getSerialNumber()),
                subjectData.getStartDate(),
                subjectData.getEndDate(),
                subjectData.getX500Principal(),
                subjectData.getPublicKey());
        addExtensions.execute(capabilities, certGen);
        X509CertificateHolder certHolder = certGen.build(contentSigner);
        JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
        certConverter = certConverter.setProvider("BC");
        return certConverter.getCertificate(certHolder);
    }

    private ContentSigner getContentSigner(PrivateKey privateKey, String algorithm) throws OperatorCreationException {
        if (algorithm.equals("RSA")) {
            return new JcaContentSignerBuilder("SHA512WITHRSAENCRYPTION").setProvider("BC").build(privateKey);
        } else {
            return new JcaContentSignerBuilder("SHA512WITHECDSA").setProvider("BC").build(privateKey);
        }
    }

}
