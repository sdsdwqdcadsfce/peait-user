package com.peait.peaituser.validata;


import com.peait.peaituser.entity.UpdateVO;
import com.peait.peaituser.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.HashMap;

@Component
public class IsExistNotIdValidata implements ConstraintValidator<IsExistNotId, Object> {

    @Resource
    private UserMapper userMapper;

    String tableName;
    String fileName;
    String message;
    String entityName;

    @Override
    public void initialize(IsExistNotId isExist) {
        fileName = isExist.fileName();
        tableName = isExist.tableName();
        message = isExist.message();
        entityName = isExist.entityName();
    }

    @Override
    public boolean isValid(Object fileValue, ConstraintValidatorContext constraintValidatorContext) {
        String idValue = "";
        HashMap<String,String> entityValueMap = new HashMap<>();
        String[] entityNameSplit = StringUtils.split(entityName, ",");
        String[] fileNameSplit = StringUtils.split(fileName, ",");
        UpdateVO file = (UpdateVO) fileValue;
        Class aClass = file.getClass();
        Field[] field = aClass.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            int size = field.length;// 属性个数
            f.setAccessible(true); // 设置些属性是可以访问的
            String type = f.getName();// 得到此属性的类型
            if(type.equals("id")){
                // 得到此属性的值
                try {
                    idValue = (String) f.get(file);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < entityNameSplit.length; j++) {
                if(type.equals(entityNameSplit[j])){
                    // 得到此属性的值
                    try {
                        entityValueMap.put(entityNameSplit[j],String.valueOf(f.get(file)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //开始查询
        for (int i = 0; i < fileNameSplit.length; i++) {
            int num = userMapper.IsExistValidataNotId(tableName,fileNameSplit[i],entityValueMap.get(entityNameSplit[i]),idValue);
            if(num!=0){
                //禁用默认的提示消息
                constraintValidatorContext.disableDefaultConstraintViolation();
                //创建新的提示消息
                constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
