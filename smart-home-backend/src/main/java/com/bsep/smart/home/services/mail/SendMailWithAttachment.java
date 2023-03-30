package com.bsep.smart.home.services.mail;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.exception.MailFailedToSendException;
import com.bsep.smart.home.model.EmailWithAttachmentDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

@Service
@RequiredArgsConstructor
public class SendMailWithAttachment {
    private final JavaMailSender javaMailSender;

    private final CustomProperties customProperties;

    @Async
    public void execute(EmailWithAttachmentDetails details) {
        sendMail(details.getRecipient(), details.getMessage(), details.getSubject(), details.getFile());
    }


    public void sendMail(String recipient, String message, String subject, File file) {
        try {
            MimeMessage mailMessage = new MimeMessage(Session.getDefaultInstance(System.getProperties()));
            mailMessage.setFrom(customProperties.getSenderEmail());
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            mailMessage.setSubject(subject);
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("certificate.crt");
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(message);
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            mailMessage.setContent(multipart);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            throw new MailFailedToSendException();
        }
    }
}
