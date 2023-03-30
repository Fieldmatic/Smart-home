package com.bsep.smart.home.services.certificate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class CreateFileFromCertificateObject {
    public File execute(X509Certificate certificate) throws CertificateEncodingException, IOException {
        byte[] certificateData = certificate.getEncoded();
        File file = new File("certificate.cer");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(certificateData);
        outputStream.close();
        return file;
    }
}
