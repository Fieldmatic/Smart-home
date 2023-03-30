package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.model.KeyEntryData;
import com.bsep.smart.home.model.SubjectData;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class CertificateGenerator {
    public X509Certificate execute(SubjectData subjectData, KeyEntryData issuerData) throws CertificateException, OperatorCreationException {
        JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
        builder = builder.setProvider("BC");
        ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());
        X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
                issuerData.getX500Principal(),
                new BigInteger(subjectData.getSerialNumber()),
                subjectData.getStartDate(),
                subjectData.getEndDate(),
                subjectData.getX500Principal(),
                subjectData.getPublicKey());

        X509CertificateHolder certHolder = certGen.build(contentSigner);
        JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
        certConverter = certConverter.setProvider("BC");
        return certConverter.getCertificate(certHolder);
    }
}
