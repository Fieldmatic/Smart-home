package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.dto.request.certificate.CreateCertificateRequest;
import com.bsep.smart.home.model.*;
import com.bsep.smart.home.repository.CSRRepository;
import com.bsep.smart.home.services.issuer.GetKeyEntryData;
import com.bsep.smart.home.services.keystore.LoadCertificateChain;
import com.bsep.smart.home.services.mail.SendMailWithAttachment;
import com.bsep.smart.home.translations.Codes;
import com.bsep.smart.home.translations.Translator;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;


@Service
@RequiredArgsConstructor
public class CreateCertificate {
    private final CSRRepository csrRepository;
    private final GetKeyEntryData getKeyEntryData;
    private final CertificateGenerator certificateGenerator;
    private final SendMailWithAttachment sendMailWithAttachment;
    private final CreateFileFromCertificateObject createFileFromCertificateObject;
    private final com.bsep.smart.home.services.keystore.SaveCertificate saveCertificate;
    private final LoadCertificateChain loadCertificateChain;

    public void execute(CreateCertificateRequest createCertificateRequest) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, OperatorCreationException, SignatureException, InvalidKeyException, NoSuchProviderException, IOException {
        CSR csr = csrRepository.getReferenceById(createCertificateRequest.getCsrId());
        KeyEntryData issuer = getKeyEntryData.execute(createCertificateRequest.getCaAlias());
        KeyEntryData subject = getKeyEntryData.execute(csr.getEmail());
        SubjectData subjectData = new SubjectData(subject.getPublicKey(), subject.getX500Principal(), createCertificateRequest.getSerialNumber(), createCertificateRequest.getStart(), createCertificateRequest.getEnd());
        X509Certificate certificate = certificateGenerator.execute(subjectData, issuer, createCertificateRequest.getCapabilities());
        X509Certificate[] chain = createChain(createCertificateRequest.getCaAlias(), certificate);
        File file = createFileFromCertificateObject.execute(certificate);
        sendMailWithAttachment.execute(new EmailWithAttachmentDetails(csr.getEmail(), Translator.toLocale(Codes.SEND_CERTIFICATE_MESSAGE), Translator.toLocale(Codes.SEND_CERTIFICATE_SUBJECT), file));
        csr.setStatus(CSRStatus.ACCEPTED);
        csrRepository.save(csr);
        saveCertificate.execute(csr.getEmail(), subject.getPrivateKey(), chain);
    }

    private X509Certificate[] createChain(String alias, X509Certificate certificate) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        Certificate[] caChain = loadCertificateChain.execute(alias);
        X509Certificate[] chain = new X509Certificate[caChain.length + 1];
        chain[0] = certificate;
        for (int i = 0; i < caChain.length; i++) {
            chain[i + 1] = (X509Certificate) caChain[i];
        }
        return chain;
    }

}
