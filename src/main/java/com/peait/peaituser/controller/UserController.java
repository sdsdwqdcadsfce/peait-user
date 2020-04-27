package com.peait.peaituser.controller;

import com.peait.peaituser.entity.LoginVO;
import com.peait.peaituser.entity.RegistVO;
import com.peait.peaituser.entity.UpdateVO;
import com.peait.peaituser.entity.User;
import com.peait.peaituser.exception.GlobalException;
import com.peait.peaituser.result.CodeMsg;
import com.peait.peaituser.result.Result;
import com.peait.peaituser.result.TableResult;
import com.peait.peaituser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @GetMapping("/getUserList")
    public TableResult getUserList(@RequestParam(value = "userName",required = false)String userName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
                                   ){
        return userService.getUserList(userName,page,limit);
    }

    @CrossOrigin
    @PostMapping("/insert")
    public Result registUser(@RequestBody @Valid RegistVO registVO , BindingResult result){
        if(result.hasErrors()){
            return Result.error(result);
        }
        return userService.insertUser(registVO);
    }


    @CrossOrigin
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody @Valid UpdateVO updateVO , BindingResult result){
        if(result.hasErrors()){
            return Result.error(result);
        }
        return userService.updateUser(updateVO);
    }


    @CrossOrigin
    @GetMapping("/queryUser")
    public Result registUser(@RequestParam(value = "id")String id){

        return userService.selectUserById(id);
    }

    @CrossOrigin
    @GetMapping("/deleteUserById")
    public Result deleteUserById(@RequestParam(value = "id")String id){

        return userService.deleteUserById(id);
    }




}
