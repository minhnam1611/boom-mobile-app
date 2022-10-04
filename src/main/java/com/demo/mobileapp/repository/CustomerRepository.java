package com.demo.mobileapp.repository;

import com.demo.mobileapp.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
    @Query(value = "SELECT * FROM Customer WHERE account_id = ?1" , nativeQuery = true)
    Customer findByAccountId(String acctId);

    Customer findByPhoneNo(String phoneNo);
}
