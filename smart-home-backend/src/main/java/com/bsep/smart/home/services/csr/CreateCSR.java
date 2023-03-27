package com.bsep.smart.home.services.csr;

import com.bsep.smart.home.model.CertificateSigningRequest;
import com.bsep.smart.home.services.keys.CreateKeyPair;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class CreateCSR {

    private final SaveCSR saveCSR;

    private final CreateKeyPair createKeyPair;

    public void execute() throws NoSuchAlgorithmException, IOException, KeyStoreException, OperatorCreationException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = createKeyPair.execute();

        X500NameBuilder x500NameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
        x500NameBuilder.addRDN(BCStyle.CN, "example.com");

        JcaPKCS10CertificationRequestBuilder csrBuilder = new JcaPKCS10CertificationRequestBuilder(x500NameBuilder.build(), keyPair.getPublic());

        PKCS10CertificationRequest csr = csrBuilder.build(new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate()));

        byte[] signedCsrBytes = csr.getEncoded();

        CertificateSigningRequest signedCsr = CertificateSigningRequest.builder().csr(signedCsrBytes).subject("example.com").build();
        saveCSR.execute(signedCsr);
    }
}
