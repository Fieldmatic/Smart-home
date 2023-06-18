package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.dto.request.certificate.CapabilityRequest;
import com.bsep.smart.home.model.Capabilities;
import com.bsep.smart.home.repository.CapabilityRepository;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddExtensions {
    private final CapabilityRepository capabilityRepository;
    
    public void execute(List<CapabilityRequest> capabilities, X509v3CertificateBuilder certGen) throws CertIOException {
        List<Capabilities> keyUsageCapabilities = new ArrayList<>();
        List<Capabilities> extendedKeyUsageCapabilities = new ArrayList<>();
        fillCapabilitiesByExtension(capabilities, keyUsageCapabilities, extendedKeyUsageCapabilities);
        if (!keyUsageCapabilities.isEmpty()) {
            certGen.addExtension(Extension.keyUsage, true, getKeyUsage(keyUsageCapabilities));
        }
        if (!extendedKeyUsageCapabilities.isEmpty()) {
            certGen.addExtension(Extension.extendedKeyUsage, true, getExtendedKeyUsage(extendedKeyUsageCapabilities));
        }
    }

    private KeyUsage getKeyUsage(List<Capabilities> keyUsageCapabilities) {
        List<KeyUsage> keyUsages = new ArrayList<>();
        for (Capabilities capability : keyUsageCapabilities) {
            switch (capability.getName()) {
                case "Signature" -> keyUsages.add(new KeyUsage(KeyUsage.digitalSignature));
                case "Non Repudiation" -> keyUsages.add(new KeyUsage(KeyUsage.nonRepudiation));
                case "Encipher Only" -> keyUsages.add(new KeyUsage(KeyUsage.encipherOnly));
                case "Key Agreement" -> keyUsages.add(new KeyUsage(KeyUsage.keyAgreement));
                case "Key Encipherment" -> keyUsages.add(new KeyUsage(KeyUsage.keyEncipherment));
            }
        }
        int mergedBits = 0;
        for (KeyUsage keyUsage : keyUsages) {
            mergedBits |= keyUsage.getBytes()[0];
        }
        return new KeyUsage(mergedBits);
    }

    private ExtendedKeyUsage getExtendedKeyUsage(List<Capabilities> extendedKeyUsageCapabilities) {
        List<KeyPurposeId> keyPurposeIds = new ArrayList<>();
        for (Capabilities capability : extendedKeyUsageCapabilities) {
            switch (capability.getName()) {
                case "Email Protection" -> keyPurposeIds.add(KeyPurposeId.id_kp_emailProtection);
                case "SSL Client Authentication" -> keyPurposeIds.add(KeyPurposeId.id_kp_clientAuth);
                case "Code Signing" -> keyPurposeIds.add(KeyPurposeId.id_kp_codeSigning);
                case "Any" -> keyPurposeIds.add(KeyPurposeId.anyExtendedKeyUsage);
            }
        }
        return new ExtendedKeyUsage(keyPurposeIds.toArray(new KeyPurposeId[0]));
    }

    private void fillCapabilitiesByExtension(List<CapabilityRequest> capabilities, List<Capabilities> keyUsageCapabilities, List<Capabilities> extendedKeyUsageCapabilities) {
        for (CapabilityRequest capabilityRequest : capabilities) {
            Capabilities capability = capabilityRepository.getReferenceById(capabilityRequest.getId());
            if (capability.getExtension().getName().equals("Key Usage")) keyUsageCapabilities.add(capability);
            else extendedKeyUsageCapabilities.add(capability);
        }
    }
}
