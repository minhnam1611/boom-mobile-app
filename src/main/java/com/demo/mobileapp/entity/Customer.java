package com.demo.mobileapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false,length = 5)
    private long id;

    @Column(name = "FULL_NAME",length = 50)
    private String fullName;

    @Column(name = "ADDRESS",length = 200)
    private String address;

    @Column(name = "PHONE_NO",length = 20)
    private String phoneNo;

    @Column(name = "EMAIL",length = 30)
    private String email;

    @Column(name = "ACCOUNT_ID",length = 20)
    private long accountId;

    @Column(name = "STATUS",length = 20)
    private String status;

    @Column(name="IS_DELETE")
    private String isDelete;

    @Column(name="CREATED_DATE")
    private Date createDate;

    @Column(name="LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    public Customer() {
    }

    public Customer(long id, String fullName, String address, String phoneNo, String email, long accountId, String isDelete, Date createDate, Date lastUpdateDate) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
        this.accountId = accountId;
        this.isDelete = isDelete;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
