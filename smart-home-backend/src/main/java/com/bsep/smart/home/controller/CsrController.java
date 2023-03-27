package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.registration.RegistrationRequest;
import com.bsep.smart.home.model.CertificateSigningRequest;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.csr.CreateCSR;
import com.bsep.smart.home.services.csr.SaveCSR;
import com.bsep.smart.home.services.registration.RegisterNewUser;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@RestController
@RequestMapping("/csr")
@RequiredArgsConstructor
public class CsrController {

    private final RegisterNewUser registerNewUser;

    private final CreateCSR createCSR;

    @PostMapping("/register")
    public void register(@RequestBody RegistrationRequest registrationRequest) throws KeyStoreException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException, OperatorCreationException, PKCSException, InvalidKeySpecException {
        Person person = registerNewUser.execute(registrationRequest);
        createCSR.execute();
        //verifikacija
//        PKCS10CertificationRequest csrDecoded = new PKCS10CertificationRequest(signedCsrBytes);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(csrDecoded.getSubjectPublicKeyInfo().getEncoded());
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey publicKey = keyFactory.generatePublic(keySpec);
//
//        boolean valid = csrDecoded.isSignatureValid(new JcaContentVerifierProviderBuilder().build(publicKey));
//        System.out.println(valid);
    }

}
