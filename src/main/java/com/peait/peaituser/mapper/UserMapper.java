package com.peait.peaituser.mapper;


import com.peait.peaituser.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int IsExistValidata(@Param("tableName") String tableName, @Param("fileName") String fileName, @Param("fileValue") Object fileValue);

    User selectByPhoneOrUserName(@Param("mobile") String mobile);

    User selectUserByUserName(@Param("userName")String userName);

    List<User> getUserList(@Param("userName")String userNamae);

    int IsExistValidataNotId(@Param("tableName") String tableName, @Param("fileName") String fileName, @Param("fileValue") Object fileValue,@Param("id")String id);
}