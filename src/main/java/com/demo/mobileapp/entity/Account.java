package com.demo.mobileapp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false,length = 5)
    private long id;

    @Column(name = "ACCOUNT_ID",length = 20)
    private String accountId;

    @Column(name = "USERNAME", length = 40)
    private String username;

    @Column(name = "ENCRYPTED_PASSWORD")
    private String encryptedPassword;

    @Column(name = "ROLE_ID", length = 20)
    private String roleId;

    @Column(name = "STATUS", length = 20)
    private String status;

    @Column(name="IS_DELETE")
    private String isDelete;

    @Column(name="CREATED_DATE")
    private Date createDate;

    @Column(name="LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    public long getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
