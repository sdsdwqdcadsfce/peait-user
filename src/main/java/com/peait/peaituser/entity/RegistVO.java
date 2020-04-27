package com.peait.peaituser.entity;

import com.peait.peaituser.validata.IsExist;
import com.peait.peaituser.validata.IsNotExist;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class RegistVO {
    @NotBlank(message = "用户名不能为空")
    @IsExist(tableName = "user",fileName = "user_name",message = "用户名已经存在")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String passWord;
    @NotBlank(message = "邮箱不能为空")
//    @IsExist(tableName = "user",fileName = "user_name",message = "用户名已经存在")
    private String email;
    @NotBlank(message = "手机号不能为空")
    @IsExist(tableName = "user",fileName = "phone",message = "手机号已经存在")
    private String phone;
}
