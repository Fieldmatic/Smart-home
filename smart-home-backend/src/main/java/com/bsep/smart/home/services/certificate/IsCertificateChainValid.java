package com.bsep.smart.home.services.certificate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.cert.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class IsCertificateChainValid {

    private final KeyStore keyStore;
    @Value("${server.ssl.key-store-password}")
    private final String keyStorePassword;

    public boolean execute(String alias) throws CertificateException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, CertPathValidatorException {
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(keyStorePassword.toCharArray()));
            X509Certificate[] chain = (X509Certificate[]) privateKeyEntry.getCertificateChain();
            List<X509Certificate> certs = new ArrayList<>(Arrays.asList(chain));
            CertPath certPath = CertificateFactory.getInstance("X.509").generateCertPath(certs);
            CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX");
            Set<TrustAnchor> trustAnchors = getTrustAnchors();
            PKIXParameters pkixParameters = new PKIXParameters(trustAnchors);
            pkixParameters.setRevocationEnabled(false);
            certPathValidator.validate(certPath, pkixParameters);
            return true;
        } catch (CertPathValidatorException | UnrecoverableEntryException e) {
            return false;
        }
    }

    private Set<TrustAnchor> getTrustAnchors() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException {
        Set<TrustAnchor> trustAnchors = new HashSet<>();
        Enumeration<String> aliases = keyStore.aliases();
        KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(keyStorePassword.toCharArray());
        while (aliases.hasMoreElements()) {
            String nextAlias = aliases.nextElement();
            if (keyStore.entryInstanceOf(nextAlias, KeyStore.PrivateKeyEntry.class)) {
                KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(nextAlias, protectionParameter);
                X509Certificate cert = (X509Certificate) entry.getCertificate();
                trustAnchors.add(new TrustAnchor(cert, null));
            }
        }
        return trustAnchors;
    }
}
