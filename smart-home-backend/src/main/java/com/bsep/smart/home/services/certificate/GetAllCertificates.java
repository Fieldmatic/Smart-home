package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.dto.response.CertificateResponse;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.keystore.LoadCertificateChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetAllCertificates {
    private final PersonRepository personRepository;
    private final CheckCertificateValidity checkCertificateValidity;
    private final LoadCertificateChain loadCertificateChain;
    private final IsCertificateRevoked isCertificateRevoked;


    public List<CertificateResponse> execute() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertPathValidatorException, InvalidAlgorithmParameterException, CertificateException, IOException, CRLException, InvalidKeySpecException {
        List<CertificateResponse> certificateResponses = new ArrayList<>();
        for (Person person : personRepository.findAll()) {
            if (!Objects.isNull(loadCertificateChain.execute(person.getEmail()))) {
                Certificate[] chain = loadCertificateChain.execute(person.getEmail());
                if (chain.length != 1) {
                    X509Certificate certificate = (X509Certificate) chain[0];
                    boolean valid = checkCertificateValidity.execute(person.getEmail());
                    boolean revoked = isCertificateRevoked.execute(person.getEmail());
                    certificateResponses.add(new CertificateResponse(person.getEmail(), extractCommonNameFromPrincipalName(certificate.getSubjectX500Principal().getName()), extractCommonNameFromPrincipalName(certificate.getIssuerX500Principal().getName()), certificate.getNotBefore(), certificate.getNotAfter(), valid, revoked));
                }
            }
        }
        return certificateResponses;
    }

    private String extractCommonNameFromPrincipalName(String x500Name) {
        return x500Name.substring(x500Name.indexOf("CN=") + 3, x500Name.indexOf(","));
    }
}
