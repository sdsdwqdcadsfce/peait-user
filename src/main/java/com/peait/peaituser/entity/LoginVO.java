package com.peait.peaituser.entity;

import com.peait.peaituser.validata.IsExist;
import com.peait.peaituser.validata.IsNotExist;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVO {
    @NotNull(message = "用户名不能为空")
    @IsNotExist(tableName = "user",fileName = "user_name",message = "用户名不存在")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String passWord;
}
