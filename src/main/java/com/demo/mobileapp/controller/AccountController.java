package com.demo.mobileapp.controller;

import com.demo.mobileapp.config.JwtTokenProvider;
import com.demo.mobileapp.contant.Contant;
import com.demo.mobileapp.contant.ResponseCode;
import com.demo.mobileapp.entity.Account;
import com.demo.mobileapp.modal.base.ResultResponse;
import com.demo.mobileapp.modal.getUserByToken.GetUserByTokenRequest;
import com.demo.mobileapp.modal.getUserByToken.GetUserByTokenResponse;
import com.demo.mobileapp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/get-account-by-token")
    @Operation(summary = "API Lấy thông tin tài khoản bằng token")
    public GetUserByTokenResponse getAccountFromToken(@RequestBody GetUserByTokenRequest getUserByTokenRequest) {
        GetUserByTokenResponse response = new GetUserByTokenResponse();
        if (StringUtils.hasText(getUserByTokenRequest.getToken()) && jwtTokenProvider.validateToken(getUserByTokenRequest.getToken())) {
            Long userId = jwtTokenProvider.getUserIdFromJWT(getUserByTokenRequest.getToken());
            Account account = accountService.findAccountById(userId).get();
            if (account != null) {
                response.setResultResponse(new ResultResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()));
                response.setAccount(account);
            }
        } else {
            response.setResultResponse(new ResultResponse(ResponseCode.TOKEN_INVALID.getCode(), ResponseCode.TOKEN_INVALID.getDesc()));
        }
        return response;
    }

    @PostMapping("/get-list-acct-by-role")
    @Operation(summary = "API Lấy danh sách tài khoản theo Role")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Account> getAccountByRoleId(@RequestParam String roleId) {
        return accountService.getAccountByRoleId(roleId);
    }

    private Account getAccountFromToken(String token) {
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            Long userId = jwtTokenProvider.getUserIdFromJWT(token);
            Account account = accountService.findAccountById(userId).get();
            return account;
        }return null;

    }


}
