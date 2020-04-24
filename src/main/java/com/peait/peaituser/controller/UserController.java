package com.peait.peaituser.controller;

import com.peait.peaituser.entity.LoginVO;
import com.peait.peaituser.entity.User;
import com.peait.peaituser.exception.GlobalException;
import com.peait.peaituser.result.CodeMsg;
import com.peait.peaituser.result.Result;
import com.peait.peaituser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    //解决跨域问题
    @CrossOrigin
    @PostMapping(value = "/login")
    public Result login(@RequestBody @Valid LoginVO loginVO, BindingResult result){
        if(result.hasErrors()){
          return Result.error(result);
        }
        return  userService.login(loginVO);

    }




}
