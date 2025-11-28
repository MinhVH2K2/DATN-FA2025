package com.example.DATN2025.service;

import com.example.DATN2025.dto.MailInfo;

import javax.mail.MessagingException;

public interface MailerService {
    void send(MailInfo mail) throws MessagingException;

    void queue(MailInfo mail);
}
