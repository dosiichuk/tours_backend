package com.tours.backend.services;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tours.backend.domain.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendEmail(Mail mail) {
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            mailSender.send(mailMessage);
        } catch (MailException e) {
            log.error("Failed to send email to {}: {}", mail.getMailTo(), e.getMessage());
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (mail.getToCc() != null && !mail.getToCc().isEmpty()) {
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }

}
