package com.demo.mobileapp.service;

import com.demo.mobileapp.contant.Contant;
import com.demo.mobileapp.entity.Customer;
import com.demo.mobileapp.modal.register.RegisterRequest;
import com.demo.mobileapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public Customer createNewCusttomer(RegisterRequest registerRequest, long acctId) {

        Customer newCust = new Customer();
        newCust.setAccountId(acctId);
        newCust.setIsDelete(Contant.STATUS_NO);
        newCust.setStatus(Contant.ProcessStatus.STATUS_INIT);
        newCust.setAddress(registerRequest.getAddress());
        newCust.setEmail(registerRequest.getEmail());
        newCust.setFullName(registerRequest.getFullname());
        newCust.setCreateDate(new Date());
        newCust.setLastUpdateDate(new Date());
        newCust.setPhoneNo(registerRequest.getPhoneNo());
        customerRepository.save(newCust);
        return newCust;

    }

    public Customer findByAccountId(long acctId){
        return customerRepository.findByAccountId(acctId);
    }

    public Customer findByPhoneNo(String phoneNo){
        return customerRepository.findByPhoneNo(phoneNo);
    }
}
