package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.service.interfaces.EmailService;
import com.project.projectbackculture.web.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${google.email}")
    private String EMAIL_USER;

    private final JavaMailSender  javaMailSender;
    private final Logger log = Logger.getLogger(EmailServiceImpl.class.getName());

    @Override
    public void sendEmail(EmailRequest emailRequest) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(EMAIL_USER);
        mailMessage.setTo(emailRequest.toUser());
        mailMessage.setSubject(emailRequest.subject());
        mailMessage.setText(emailRequest.message());

        javaMailSender.send(mailMessage);
        log.info("Email sent successfully");
    }
}
