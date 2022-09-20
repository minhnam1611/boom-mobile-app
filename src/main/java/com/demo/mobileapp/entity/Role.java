package com.demo.mobileapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false,length = 5)
    private long id;

    @Column(name = "ROLE_ID", length = 20)
    private String roleId;

    @Column(name = "ROLE_NAME", length = 40)
    private String roleName;

    @Column(name="IS_DELETE")
    private String isDelete;

    @Column(name="CREATED_DATE")
    private Date createDate;

    @Column(name="LAST_UPDATE_DATE")
    private Date lastUpdateDate;

}
