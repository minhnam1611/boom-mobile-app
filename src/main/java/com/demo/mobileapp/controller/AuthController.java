package com.demo.mobileapp.controller;

import com.demo.mobileapp.config.JwtTokenProvider;
import com.demo.mobileapp.contant.ResponseCode;
import com.demo.mobileapp.entity.Account;
import com.demo.mobileapp.entity.Customer;
import com.demo.mobileapp.modal.CustomUserDetails;
import com.demo.mobileapp.modal.ResultResponse;
import com.demo.mobileapp.modal.changepasword.ChangePasswordRequest;
import com.demo.mobileapp.modal.changepasword.ChangePasswordResponse;
import com.demo.mobileapp.modal.login.LoginRequest;
import com.demo.mobileapp.modal.login.LoginResponse;
import com.demo.mobileapp.modal.register.RegisterRequest;
import com.demo.mobileapp.modal.register.RegisterResponse;
import com.demo.mobileapp.service.AccountService;
import com.demo.mobileapp.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MailSender mailSender;

    public static final String SUBJECT_EMAIL = "Register Account: Send Password";


    @PostMapping("/register")
    @Operation(summary = "API đăng ký tài Khoản")
    public RegisterResponse validateRegister(@RequestBody RegisterRequest registerRequest) {
        Optional<Account> checkUsername = accountService.findByUserName(registerRequest.getUsername());
        Customer checkPhone = customerService.findByPhoneNo(registerRequest.getPhoneNo());
        if (checkUsername.isPresent()) {
            RegisterResponse response = new RegisterResponse();
            response.setResultResponse(new ResultResponse(ResponseCode.USERNAME_EXISTED.getCode(), ResponseCode.USERNAME_EXISTED.getDesc()));
            return response;
        } else if (checkPhone != null) {
            RegisterResponse response = new RegisterResponse();
            response.setResultResponse(new ResultResponse(ResponseCode.PHONE_NO_EXISTED.getCode(), ResponseCode.PHONE_NO_EXISTED.getDesc()));
            return response;
        } else {
            Account newAccount = accountService.createAccount(registerRequest);
            Random rand = new Random();
            int ranNum = rand.nextInt(10000) + 1;
            String newPass = "Boom$" + ranNum;
            newAccount.setEncryptedPassword(new BCryptPasswordEncoder().encode(newPass));
            long acctId = newAccount.getId();
            Customer newCustomer = customerService.createNewCusttomer(registerRequest, acctId);
            long custId = newCustomer.getId();
            RegisterResponse response = new RegisterResponse();
            response.setResultResponse(new ResultResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()));
            response.setAccountId(acctId);
            response.setCustId(custId);

            //Send Mail password
            sendEmail("Boom-Shop", newCustomer.getEmail(), SUBJECT_EMAIL, contentMailConfirmRegister(newAccount, newPass));

            return response;
        }
    }

    @PostMapping("/login")
    @Operation(summary = "API đăng nhập")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        CustomUserDetails customUserDetails = (CustomUserDetails) accountService.loadUserByUsername(loginRequest.getUsername().toLowerCase());
        if (null == customUserDetails || !new BCryptPasswordEncoder().matches(loginRequest.getPassword(), customUserDetails.getPassword())) {
            LoginResponse response = new LoginResponse();
            response.setResultResponse(new ResultResponse(ResponseCode.LOGIN_INVALID.getCode(), ResponseCode.LOGIN_INVALID.getDesc()));
            return response;
        }
        String jwt = jwtTokenProvider.generateToken(customUserDetails);

        LoginResponse response = new LoginResponse();
        response.setResultResponse(new ResultResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()));
        response.setToken(jwt);
        return response;
    }

    @PostMapping("/change-pass")
    @Operation(summary = "API đổi mật khẩu")
    public ChangePasswordResponse ChagePass(@RequestBody ChangePasswordRequest changePasswordRequest) {
        ChangePasswordResponse response = new ChangePasswordResponse();
        if (StringUtils.hasText(changePasswordRequest.getToken()) && jwtTokenProvider.validateToken(changePasswordRequest.getToken())) {
            Long userId = jwtTokenProvider.getUserIdFromJWT(changePasswordRequest.getToken());
            Account account = accountService.findAccountById(userId).get();
            if (account != null) {
                if (new BCryptPasswordEncoder().matches(changePasswordRequest.getOldPass(), account.getEncryptedPassword())) {
                    account.setEncryptedPassword(new BCryptPasswordEncoder().encode(changePasswordRequest.getNewPass()));
                    accountService.saveAccount(account);
                    response.setResultResponse(new ResultResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()));
                } else {
                    response.setResultResponse(new ResultResponse(ResponseCode.OLD_PASS_INVALID.getCode(), ResponseCode.OLD_PASS_INVALID.getDesc()));
                }

            }
        } else {
            response.setResultResponse(new ResultResponse(ResponseCode.TOKEN_INVALID.getCode(), ResponseCode.TOKEN_INVALID.getDesc()));
        }
        return response;

    }

    public void sendEmail(String from, String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        mailSender.send(simpleMailMessage);
    }

    private String contentMailConfirmRegister(Account account, String newPass) {
        return "Register Success! \n" +
                "Your Account: " + account.getUsername() + "/" + newPass + "\n" +
                "Please login Boom-App and Change new password \n" +
                "Thanks you!!!";
    }


}
