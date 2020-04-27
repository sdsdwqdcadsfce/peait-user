package com.peait.peaituser.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peait.peaituser.validata.IsExist;
import lombok.Data;

@Data
public class User {
    private String id;
    @IsExist(tableName = "user",fileName = "user_name")
    private String userName;
    @JsonIgnore
    private String passWord;

    private String userEmail;

    private String vxCode;
    @IsExist(tableName = "user",fileName = "phone")
    private String phone;

    private Integer userVocation;

    private Integer userSex;


}