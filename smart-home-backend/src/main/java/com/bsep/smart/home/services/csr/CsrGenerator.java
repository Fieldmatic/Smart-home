package com.bsep.smart.home.services.csr;

import com.bsep.smart.home.dto.request.csr.CSRRequest;
import com.bsep.smart.home.services.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
@RequiredArgsConstructor
public class CsrGenerator {

    private final GetUserByEmail getUserByEmail;

    public void execute(KeyPair keyPair, String email, CSRRequest csrRequest) throws OperatorCreationException {
        X500Name subject = createName(email, csrRequest).build();
        PKCS10CertificationRequestBuilder requestBuilder = new JcaPKCS10CertificationRequestBuilder(subject, keyPair.getPublic());
        JcaContentSignerBuilder signerBuilder = new JcaContentSignerBuilder("SHA256withRSA");
        ContentSigner signer = signerBuilder.build(keyPair.getPrivate());
        PKCS10CertificationRequest csr = requestBuilder.build(signer);

    }

    private X500NameBuilder createName(String email, CSRRequest csr) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, csr.getCommonName());
        builder.addRDN(BCStyle.O, csr.getOrganization());
        builder.addRDN(BCStyle.OU, csr.getOrganizationalUnit());
        builder.addRDN(BCStyle.C, csr.getCountry());
        builder.addRDN(BCStyle.ST, csr.getState());
        builder.addRDN(BCStyle.L, csr.getCity());
        builder.addRDN(BCStyle.EmailAddress, email);
        builder.addRDN(BCStyle.UID, getUserByEmail.execute(email).getId().toString());
        return builder;
    }
}
