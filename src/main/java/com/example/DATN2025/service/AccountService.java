package com.example.DATN2025.service;


import com.example.DATN2025.dto.Statistic.UserStatistic;
import com.example.DATN2025.entity.Account;

import java.util.List;

public interface AccountService {
    Account findByEmail(String email);

    List<Account> findAllAccount();
    Account save(Account account);

    List<UserStatistic> getUserStatistics(String startDate, String endDate);

    Account blockAccount(Long id);

    Account openAccount(Long id);

    Account changeRole(String email, Long roleId);
//
//    AccountDto getAccountLogin();
//
//    AccountDto updateProfile(AccountDto accountDto);
//
//    void changePassword(ChangePasswordDto changePasswordDto);
//
//    void resetPassword(Account account, String newPassword);
}
