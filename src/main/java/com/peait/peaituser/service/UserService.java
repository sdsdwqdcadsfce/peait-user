package com.peait.peaituser.service;

import com.peait.peaituser.entity.LoginVO;
import com.peait.peaituser.entity.RegistVO;
import com.peait.peaituser.entity.UpdateVO;
import com.peait.peaituser.result.Result;
import com.peait.peaituser.result.TableResult;

public interface UserService {


    Result login(LoginVO loginVO);

    TableResult getUserList(String userNamae, int page, int limit);

    Result insertUser(RegistVO registVO);

    Result selectUserById(String id);

    Result updateUser(UpdateVO registVO);

    Result deleteUserById(String id);
}
