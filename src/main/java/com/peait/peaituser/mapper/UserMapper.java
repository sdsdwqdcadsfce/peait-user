package com.peait.peaituser.mapper;


import com.peait.peaituser.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int IsExistValidata(@Param("tableName") String tableName, @Param("fileName") String fileName, @Param("fileValue") Object fileValue);

    User selectByPhoneOrUserName(@Param("mobile") String mobile);

    User selectUserByUserName(@Param("userName")String userName);
}