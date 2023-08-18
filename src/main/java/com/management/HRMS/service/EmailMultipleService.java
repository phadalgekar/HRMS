package com.management.HRMS.service;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailMultipleService {

    private final JavaMailSender mailSender;

    public EmailMultipleService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(List<String> recipientEmails, String subject, String body,
                                      byte[] imgBytes, byte[] docBytes) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true); // Use true for multipart message

            String[] recipientArray = recipientEmails.toArray(new String[0]);
            helper.setTo(recipientArray);
            helper.setSubject(subject);
            helper.setText(body, true); // Use true for HTML content

            // Attach the image if available
            if (imgBytes != null) {
                helper.addAttachment("image.jpg", new ByteArrayResource(imgBytes));
            }

            // Attach the document if available
            if (docBytes != null) {
                helper.addAttachment("document.pdf", new ByteArrayResource(docBytes));
            }

            mailSender.send(message);
        } catch (MessagingException e) {
            // Log the error or handle it as needed
            System.err.println("Error sending email: " + e.getMessage());
            // You might want to consider logging the error using a logging framework
            // and/or throwing a custom exception to handle the error at a higher level.
        }
    }
}
