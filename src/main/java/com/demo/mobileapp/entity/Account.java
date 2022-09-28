package com.demo.mobileapp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
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

    @Column(name = "AVATAR_BASE64")
    private String avatarBase64;


}
