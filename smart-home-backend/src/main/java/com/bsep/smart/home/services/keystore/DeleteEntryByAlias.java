package com.bsep.smart.home.services.keystore;

import com.bsep.smart.home.model.EmailDetails;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.repository.CSRRepository;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.mail.SendMail;
import com.bsep.smart.home.translations.Codes;
import com.bsep.smart.home.translations.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Service
@RequiredArgsConstructor
public class DeleteEntryByAlias {
    private final KeyStore keyStore;

    private final SaveKeystore saveKeystore;
    private final SendMail sendMail;
    private final CSRRepository csrRepository;
    private final PersonRepository personRepository;

    @Transactional
    public void execute(String alias) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        keyStore.deleteEntry(alias);
        Person person = personRepository.findByEmail(alias).get();
        person.setCsr(null);
        final EmailDetails emailDetails = new EmailDetails(alias, Translator.toLocale(
                Codes.CERTIFICATE_WITHDRAWAL_MESSAGE), Translator.toLocale(Codes.CERTIFICATE_WITHDRAWAL_SUBJECT));
        sendMail.execute(emailDetails);
        personRepository.save(person);
        saveKeystore.execute();
    }
}
