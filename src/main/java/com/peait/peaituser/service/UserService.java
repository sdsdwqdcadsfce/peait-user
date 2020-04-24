package com.peait.peaituser.service;

import com.peait.peaituser.entity.LoginVO;
import com.peait.peaituser.entity.User;
import com.peait.peaituser.result.Result;

public interface UserService {


    Result login(LoginVO loginVO);
}
