package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.web.request.EmailRequest;

public interface EmailService {

    void sendEmail(EmailRequest emailRequest);
}
