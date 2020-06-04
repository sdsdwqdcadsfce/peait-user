package com.peait.peaituser.entity;

import com.peait.peaituser.validata.IsExist;
import com.peait.peaituser.validata.IsExistNotId;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@IsExistNotId(tableName = "user",fileName = "user_name,user_email,phone",entityName = "userName,email,phone")
public class UpdateVO {
    @NotBlank(message = "id不能为空")
    private String id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
