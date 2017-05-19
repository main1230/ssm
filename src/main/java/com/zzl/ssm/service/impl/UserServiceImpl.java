package com.zzl.ssm.service.impl;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.dao.UserMapper;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.IUserService;
import com.zzl.ssm.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2016/12/5.
 */
@Service
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        logger.debug("--login---username=" + username + "  password=" + password);
        if (username == null || username.equals("") || password == null || password.equals("")) {
            return ServerResponse.error();
        }

        int resultCount = userMapper.checkUsername(username);
        if (resultCount < 1) {
            return ServerResponse.errorMsg("用户名不存在~");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.errorMsg("密码错误~");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.successData(user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse validResponse = this.checkValid(user.getUsername(), Constent.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Constent.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        user.setRole(Constent.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.successMsg("注册失败");
        }
        return ServerResponse.successMsg("注册成功");
    }

    public ServerResponse<String> checkValid(String str, String type) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
            //开始校验
            if (Constent.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.errorMsg("用户名已存在");
                }
            }
            if (Constent.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.errorMsg("email已存在");
                }
            }
        } else {
            return ServerResponse.errorMsg("参数错误");
        }
        return ServerResponse.successMsg("校验成功");
    }

}
