package com.bsep.smart.home.services.keystore;

import com.bsep.smart.home.dto.request.csr.CSRRequest;
import com.bsep.smart.home.services.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AddKeyPair {
    private final GetUserByEmail getUserByEmail;
    private final SaveCertificate saveCertificate;

    public void execute(KeyPair keyPair, String email, CSRRequest csr) throws CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, OperatorCreationException, KeyStoreException, IOException {
        X500NameBuilder builder = createName(email, csr);
        X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(
                builder.build(),
                BigInteger.valueOf(new Random().nextInt()),
                new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24),
                new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365),
                builder.build(),
                SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded())
        );
        ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSAEncryption")
                .setProvider("BC").build(keyPair.getPrivate());
        X509CertificateHolder certHolder = certBuilder.build(signer);
        X509Certificate cert = new JcaX509CertificateConverter()
                .setProvider("BC").getCertificate(certHolder);
        saveCertificate.execute(email, keyPair.getPrivate(), new X509Certificate[]{cert});
    }

    private X500NameBuilder createName(String email, CSRRequest csr) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.EmailAddress, email);
        builder.addRDN(BCStyle.UID, getUserByEmail.execute(email).getId().toString());
        builder.addRDN(BCStyle.C, csr.getCountry());
        builder.addRDN(BCStyle.ST, csr.getState());
        builder.addRDN(BCStyle.L, csr.getCity());
        builder.addRDN(BCStyle.O, csr.getOrganization());
        builder.addRDN(BCStyle.OU, csr.getOrganizationalUnit());
        builder.addRDN(BCStyle.CN, csr.getCommonName());
        return builder;
    }

}
