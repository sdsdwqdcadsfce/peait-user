package com.peait.peaituser.service.impl;

import com.peait.peaituser.entity.LoginVO;
import com.peait.peaituser.entity.User;
import com.peait.peaituser.exception.GlobalException;
import com.peait.peaituser.mapper.UserMapper;
import com.peait.peaituser.result.CodeMsg;
import com.peait.peaituser.result.Result;
import com.peait.peaituser.service.UserService;
import com.peait.peaituser.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;



    /**
     * 登陆操作
     *
     * @param loginVO
     * @return
     */
    @Override
    public Result login(LoginVO loginVO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userName = loginVO.getUserName();
        String passWord = loginVO.getPassWord();
        User user = userMapper.selectUserByUserName(userName);
        boolean matches = bCryptPasswordEncoder.matches(passWord, user.getPassWord());
        if (!matches) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
////        addCookie(response, token, user);
        //返回登陆成功
        return Result.success(token);
    }


    /**
     * 添加token
     */
//    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
//        redisService.set(MiaoshaUserKey.token, token, user);
//        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
//        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
//        cookie.setPath("/");
//        response.addCookie(cookie);
//    }


}
