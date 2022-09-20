package com.demo.mobileapp.service;

import com.demo.mobileapp.entity.Account;
import com.demo.mobileapp.modal.CustomUserDetails;
import com.demo.mobileapp.modal.ResultResponse;
import com.demo.mobileapp.modal.register.RegisterRequest;
import com.demo.mobileapp.modal.register.RegisterResponse;
import com.demo.mobileapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOpn = accountRepository.findByUserName(username);
        if (accountOpn.isPresent()) {
            Account account = accountOpn.get();
            if (account == null) {
                return null;
            }
            return new CustomUserDetails(account);
        }
        return null;

    }
    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public Optional<Account> findByUserName(String username){
        return accountRepository.findByUserName(username);
    }

    public UserDetails loadUserById(Long id) throws Exception {
        Account account = accountRepository.findById(id).get();
        if (account == null) {
            throw new Exception("Not found user_id : " + id);
        }
        return new CustomUserDetails(account);
    }

    public Account createAccount(RegisterRequest registerRequest) {
        Account newAccount = new Account();
        newAccount.setUsername(registerRequest.getUsername());
        newAccount.setAccountId(registerRequest.getUsername().toUpperCase());
        newAccount.setRoleId("USER");
        newAccount.setIsDelete("N");
        newAccount.setStatus("INIT");
        newAccount.setCreateDate(new Date());
        newAccount.setLastUpdateDate(new Date());
        this.accountRepository.save(newAccount);
        return newAccount;
    }



    public Optional<Account> findAccountById(Long accountId) {
        return this.accountRepository.findById(accountId);
    }

    public List<Account> getAccountByRoleId(String roleId) {
        return this.accountRepository.getAccountByRoleId(roleId);
    }

}
