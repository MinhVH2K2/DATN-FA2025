package com.example.DATN2025.service;


import com.example.DATN2025.entity.Account;
import com.example.DATN2025.entity.VerificationCode;

import javax.mail.MessagingException;

public interface VerificationCodeService {
    VerificationCode createVerificationCode(String email) throws MessagingException;

    Account verifyCode(String code);
}