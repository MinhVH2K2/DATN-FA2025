package com.example.DATN2025.utils;


import com.example.DATN2025.entity.Account;
import com.example.DATN2025.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserLoginUtil {
    public static Account getCurrentLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return customUserDetails.getAccount();
        }

        // Handle the case where the principal is not a CustomUserDetails
        return null; // or throw an exception, depending on your use case
    }
}
