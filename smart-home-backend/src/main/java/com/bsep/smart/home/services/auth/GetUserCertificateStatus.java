package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.UserCertificateStatus;
import com.bsep.smart.home.services.certificate.CheckCertificateValidity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetUserCertificateStatus {
    private final CheckCertificateValidity checkCertificateValidity;

    public UserCertificateStatus execute(Person person) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateNotYetValidException, CertificateExpiredException {
        if (person.getRole().getName().equals("ADMIN")) return UserCertificateStatus.ACCEPTED_CSR;
        CSR csr = person.getCsr();
        if (Objects.isNull(csr)) return UserCertificateStatus.NO_CSR;
        else if (csr.getStatus().equals(CSRStatus.REJECTED)) return UserCertificateStatus.NO_CSR;
        else if (csr.getStatus().equals(CSRStatus.PENDING)) return UserCertificateStatus.PENDING_CSR;
        else {
            try {
                boolean validity = checkCertificateValidity.execute(csr.getEmail());
                if (validity) return UserCertificateStatus.ACCEPTED_CSR;
                else return UserCertificateStatus.PENDING_CSR;
            } catch (Exception e) {
                return UserCertificateStatus.PENDING_CSR;
            }
        }


    }
}
