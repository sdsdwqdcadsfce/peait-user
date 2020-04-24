package com.peait.peaituser.validata;



import com.peait.peaituser.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IsExistValidata implements ConstraintValidator<IsExist, Object> {

    @Resource
    private UserMapper userMapper;

    String tableName;
    String fileName;
    String message;

    @Override
    public void initialize(IsExist isExist) {
        fileName = isExist.fileName();
        tableName = isExist.tableName();
        message = isExist.message();
    }

    @Override
    public boolean isValid(Object fileValue, ConstraintValidatorContext constraintValidatorContext) {
        int i = userMapper.IsExistValidata(tableName,fileName,fileValue);
        if(i==0){
            return true;
        }
        //禁用默认的提示消息
        constraintValidatorContext.disableDefaultConstraintViolation();
        //创建新的提示消息
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
