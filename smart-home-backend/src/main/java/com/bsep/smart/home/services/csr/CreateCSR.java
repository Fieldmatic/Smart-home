package com.bsep.smart.home.services.csr;

import com.bsep.smart.home.dto.request.csr.CSRRequest;
import com.bsep.smart.home.exception.UserHasPendingCsrException;
import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import com.bsep.smart.home.services.keys.CreateKeyPair;
import com.bsep.smart.home.services.keystore.AddKeyPair;
import com.bsep.smart.home.services.user.SaveUser;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateCSR {
    private final GetLoggedInUser getLoggedInUser;
    private final SaveUser saveUser;
    private final CreateKeyPair createKeyPair;
    private final AddKeyPair addKeyPair;

    public void execute(CSRRequest csrRequest) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, OperatorCreationException, IOException {
        Person person = getLoggedInUser.execute();
        if (!Objects.isNull(person.getCsr())) throw new UserHasPendingCsrException();
        KeyPair keyPair = createKeyPair.execute(csrRequest.getAlgorithm(), csrRequest.getKeySize());
        addKeyPair.execute(keyPair, person.getEmail(), csrRequest);
        CSR csr = CSR.builder()
                .email(person.getEmail())
                .commonName(csrRequest.getCommonName())
                .organization(csrRequest.getOrganization())
                .organizationalUnit(csrRequest.getOrganizationalUnit())
                .city(csrRequest.getCity())
                .state(csrRequest.getState())
                .country(csrRequest.getCountry())
                .keySize(csrRequest.getKeySize())
                .algorithm(csrRequest.getAlgorithm())
                .status(CSRStatus.PENDING)
                .build();
        person.setCsr(csr);
        saveUser.execute(person);
    }
}
