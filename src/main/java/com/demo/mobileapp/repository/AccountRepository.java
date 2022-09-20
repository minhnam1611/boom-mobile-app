package com.demo.mobileapp.repository;
import com.demo.mobileapp.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {

    @Query("select u from Account u where " +
            " u.username like  %:keySearch% or " +
            " u.roleId like  %:keySearch%")
    Page<Account> searchAccounts(@Param("keySearch") String keySearch, Pageable pageable);

    @Query("select u from Account u where u.isDelete = 1")
    Page<Account> getAllAccount(Pageable pageable);

    @Query("select u from Account u where u.username like :keyUsername")
    Optional<Account> findByUserName(@Param("keyUsername") String keyUsername);

    @Query("select a from Account  a where  a.roleId='ADMIN'")
    Set<Account> findByRole();

    @Query(value = "SELECT * FROM Account WHERE role_id = ?1",nativeQuery = true)
    List<Account> getAccountByRoleId(String roleId);

    Optional<Account> findAccountById(Integer id);
}
