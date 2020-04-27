package com.peait.peaituser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.peaituser.entity.LoginVO;
import com.peait.peaituser.entity.RegistVO;
import com.peait.peaituser.entity.UpdateVO;
import com.peait.peaituser.entity.User;
import com.peait.peaituser.exception.GlobalException;
import com.peait.peaituser.mapper.UserMapper;
import com.peait.peaituser.result.CodeMsg;
import com.peait.peaituser.result.Result;
import com.peait.peaituser.result.TableResult;
import com.peait.peaituser.service.UserService;
import com.peait.peaituser.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
//        String token = UUIDUtil.uuid();
        String token = "111";
////        addCookie(response, token, user);
        //返回登陆成功
        return Result.success(token);
    }

    /**
     * 获取用户列表
     * @param userNamae
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult getUserList(String userNamae, int page, int limit) throws GlobalException {
        PageHelper.startPage(page, limit);
        List<User> result = userMapper.getUserList(userNamae);
        PageInfo<User> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(),userPageInfo.getList());
    }

    /**
     * 新增用户
     * @param registVO
     * @return
     */
    @Override
    public Result insertUser(RegistVO registVO) throws GlobalException{
        User user = new User();
        user.setId(UUIDUtil.uuid());
        user.setUserEmail(registVO.getEmail());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(registVO.getPassWord());
        user.setPassWord(encode);
        BeanUtils.copyProperties(registVO,user);
        userMapper.insertSelective(user);
        return Result.success(new ArrayList<>());
    }

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    @Override
    public Result selectUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return Result.success(user);
    }

    /**
     * 更新
     * @param registVO
     * @return
     */
    @Override
    public Result updateUser(UpdateVO registVO) {
        User user = new User();
        BeanUtils.copyProperties(registVO,user);
        user.setId(registVO.getId());
        user.setUserEmail(registVO.getEmail());
        userMapper.updateByPrimaryKeySelective(user);
        return Result.success("");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public Result deleteUserById(String id) {
        userMapper.deleteByPrimaryKey(id);
        return Result.success("");
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
