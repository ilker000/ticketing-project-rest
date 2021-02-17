package com.spring.service;

import com.spring.entity.ConfirmationToken;
import org.springframework.mail.SimpleMailMessage;

public interface ConfirmationTokenService {

    ConfirmationToken save(ConfirmationToken confirmationToken);

    void sendEmail(SimpleMailMessage email);

    ConfirmationToken readByToken(String token);

    void delete(ConfirmationToken confirmationToken);

}
