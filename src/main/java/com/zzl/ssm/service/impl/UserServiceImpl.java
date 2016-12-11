package com.zzl.ssm.service.impl;

import com.zzl.ssm.dao.UserDao;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2016/12/5.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    public List<User> getUsers(int pageNum, int pageSize) {
        int offset = pageNum - 1;
        int limit = pageSize * pageNum;
        List<User> list = userDao.queryUsers(offset, limit);
        return list;
    }

    public User getUserbyId(int id) {
        User bean = userDao.getUserById(id);
        return bean;
    }

    public User login(String phone, String password) {
        User bean = userDao.login(phone, password);
        return bean;
    }

    public int register() {
        return 0;
    }

    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    public int updateUser(String name, String phone, String password, String headImage) {
        return userDao.updateUser(name, phone, password, headImage);
    }
}
