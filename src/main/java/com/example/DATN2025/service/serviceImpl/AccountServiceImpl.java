package com.example.DATN2025.service.serviceImpl;

import com.example.DATN2025.dto.Statistic.UserStatistic;
import com.example.DATN2025.entity.Account;
import com.example.DATN2025.entity.Role;
import com.example.DATN2025.repository.AccountRepository;
import com.example.DATN2025.repository.CustomerRepository;
import com.example.DATN2025.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    @Autowired
//    private AddressShippingRepository addressShippingRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public List<UserStatistic> getUserStatistics(String startDate, String endDate) {
        List<UserStatistic> userStatistics = new ArrayList<>();

        List<Object[]> results = accountRepository.getMonthlyAccountStatistics(startDate, endDate);


        for (Object[] result : results) {
            String month = (String) result[0];
            Integer count = ((Number) result[1]).intValue();
            userStatistics.add(new UserStatistic(month, count));
        }

        return userStatistics;
    }

    @Override
    public Account blockAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(null);
        account.setNonLocked(false);
        return accountRepository.save(account);
    }

    @Override
    public Account openAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(null);
        account.setNonLocked(true);
        return accountRepository.save(account);
    }

    @Override
    public Account changeRole(String email, Long roleId) {
        Account account = accountRepository.findByEmail(email);
        Role role = new Role();
        role.setId(roleId);
        account.setRole(role);
        return accountRepository.save(account);
    }

//    @Override
//    public Account getAccountLogin() {
//        Account account = UserLoginUtil.getCurrentLogin();
//        Customer customer = customerRepository.findByAccount_Id(account.getId());
//        account.setCustomer(customer);
//        return convertToDto(account);
//    }
//
//    @Override
//    public AccountDto updateProfile(AccountDto accountDto) {
//        Account account = UserLoginUtil.getCurrentLogin();
//        Customer customer = customerRepository.findByAccount_Id(account.getId());
//        if(!accountDto.getPhoneNumber().trim().equals(customer.getPhoneNumber())) {
//            if(customerRepository.existsByPhoneNumber(accountDto.getPhoneNumber())) {
//                throw new ShopApiException(HttpStatus.BAD_REQUEST, "Số điện thoại " + accountDto.getPhoneNumber() + " đã được đăng ký");
//            }
//        }
//        customer.setPhoneNumber(accountDto.getPhoneNumber());
//        customer.setName(accountDto.getName());
//        customerRepository.save(customer);
//        return convertToDto(accountRepository.save(account));
//    }
//
//    @Override
//    public void changePassword(ChangePasswordDto changePasswordDto) {
//        Account account = UserLoginUtil.getCurrentLogin();
//        // Kiểm tra mật khẩu hiện tại
//        if (!passwordEncoder.matches(changePasswordDto.getCurrentPassword(), account.getPassword())) {
//            throw new ShopApiException(HttpStatus.BAD_REQUEST, "Mật khẩu hiện tại không chính xác");
//        }
//
//        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
//        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
//            throw new ShopApiException(HttpStatus.BAD_REQUEST, "Xác nhận mật khẩu không khớp");
//        }
//        account.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
//        accountRepository.save(account);
//    }
//
//    @Override
//    public void resetPassword(Account account, String newPassword) {
//        account.setPassword(passwordEncoder.encode(newPassword));
//        accountRepository.save(account);
//    }
//
//    private AccountDto convertToDto(Account account) {
//        AccountDto accountDto = new AccountDto();
//        accountDto.setEmail(account.getEmail());
//        accountDto.setName(account.getCustomer().getName());
//        accountDto.setPhoneNumber(account.getCustomer().getPhoneNumber());
//        List<AddressShippingDto> addressShippingDtos = new ArrayList<>();
//        List<AddressShipping> addressShippingList = addressShippingRepository.findAllByCustomer_Account_Id(account.getId());
//        for (AddressShipping addressShipping:
//             addressShippingList) {
//            AddressShippingDto addressShippingDto = new AddressShippingDto();
//            addressShippingDto.setId(addressShipping.getId());
//            addressShippingDto.setAddress(addressShipping.getAddress());
//            addressShippingDtos.add(addressShippingDto);
//        }
//        accountDto.setAddressShippingList(addressShippingDtos);
//        return accountDto;
//    }
//
//    private Account convertToEntity(AccountDto accountDto) {
//        Account account = new Account();
//        account.setUpdateDate(LocalDateTime.now());
//        account.setEmail(accountDto.getEmail());
//        return account;
//    }
}
