package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.dto.request.certificate.RevokeRequest;
import com.bsep.smart.home.model.EmailDetails;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.keystore.LoadPrivateKey;
import com.bsep.smart.home.services.keystore.LoadX509Certificate;
import com.bsep.smart.home.services.mail.SendMail;
import com.bsep.smart.home.translations.Codes;
import com.bsep.smart.home.translations.Translator;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RevokeCertificate {
    private final LoadX509Certificate loadX509Certificate;
    private final ResourceLoader resourceLoader;
    private final LoadPrivateKey loadPrivateKey;
    private final PersonRepository personRepository;
    private final SendMail sendMail;

    public void execute(RevokeRequest revokeRequest) throws Exception {
        X509Certificate certificate = loadX509Certificate.execute(revokeRequest.getAlias());
        InputStream crlStream = resourceLoader.getResource("classpath:keystore/crl.crl").getInputStream();
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509CRL crl = (X509CRL) cf.generateCRL(crlStream);
        crl = updateCRL(crl, certificate, revokeRequest.getReason());
        FileOutputStream outStream = new FileOutputStream(resourceLoader.getResource("classpath:keystore/crl.crl").getFile());
        outStream.write(crl.getEncoded());
        outStream.close();
        Person person = personRepository.findByEmail(revokeRequest.getAlias()).get();
        person.setCsr(null);
        final EmailDetails emailDetails = new EmailDetails(revokeRequest.getAlias(), Translator.toLocale(
                Codes.CERTIFICATE_REVOKED_MESSAGE, new String[]{revokeRequest.getMessage()}), Translator.toLocale(Codes.CERTIFICATE_REVOKED_SUBJECT));
        sendMail.execute(emailDetails);
        personRepository.save(person);

    }

    private X509CRL updateCRL(X509CRL crl, X509Certificate certificate, int reasonNumber) throws Exception {
        X509v2CRLBuilder crlBuilder = new X509v2CRLBuilder(new X500Name(crl.getIssuerX500Principal().getName()), crl.getThisUpdate());
        crlBuilder.addCRL(new X509CRLHolder(crl.getEncoded()));
        JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();
        ASN1Enumerated reason = new ASN1Enumerated(reasonNumber);
        SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(certificate.getPublicKey().getEncoded());
        byte[] subjectKeyIdentifier = extUtils.createSubjectKeyIdentifier(publicKeyInfo).getEncoded();
        Extension reasonExtension = new Extension(Extension.reasonCode, false, reason.getEncoded());
        Extension subjectKeyExtension = new Extension(Extension.subjectKeyIdentifier, false, subjectKeyIdentifier);
        crlBuilder.addCRLEntry(certificate.getSerialNumber(), new Date(), new Extensions(new Extension[]{reasonExtension, subjectKeyExtension}));
        crlBuilder.setNextUpdate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        ContentSigner signer = getContentSigner(loadPrivateKey.execute("intermediate1"), certificate.getSigAlgName());
        JcaX509CRLConverter converter = new JcaX509CRLConverter();
        return converter.getCRL(crlBuilder.build(signer));
    }

    private ContentSigner getContentSigner(PrivateKey privateKey, String algorithm) throws OperatorCreationException {
        if (algorithm.toLowerCase().contains("sha")) {
            return new JcaContentSignerBuilder("SHA512WITHRSAENCRYPTION").setProvider("BC").build(privateKey);
        } else {
            return new JcaContentSignerBuilder("SHA512WITHECDSA").setProvider("BC").build(privateKey);
        }
    }

}
