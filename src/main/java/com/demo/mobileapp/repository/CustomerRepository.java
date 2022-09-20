package com.demo.mobileapp.repository;

import com.demo.mobileapp.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
    Customer findByAccountId(long acctId);

    Customer findByPhoneNo(String phoneNo);
}
