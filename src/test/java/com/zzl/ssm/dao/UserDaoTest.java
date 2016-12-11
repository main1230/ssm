package com.zzl.ssm.dao;

import com.zzl.ssm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangzl
 * 描述：测试user
 * 日期：  2016/12/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void getUserById() throws Exception {
        int id = 1;
        User user = userDao.getUserById(id);
        System.out.println("-----------" + user);
    }

    @Test
    public void login() throws Exception {
        String phone = "12345678900";
        String password = "ppppppppppppp";
        User user = userDao.login(phone, password);
        System.out.println("----login--" + user.toString());
    }

    @Test
    public void register() throws Exception {
        String name = "哈哈";
        String phone = "12345678900";
        String password = "ppppppppppppp";
        int result = userDao.register(name, phone, password);
        System.out.println("----register--" + result);
    }

    @Test
    public void deleteUserById() throws Exception {
        int id = 1;
        int result = userDao.deleteUserById(id);
        System.out.println("---deleteUserById---"+result);
    }

    @Test
    public void queryUsers() throws Exception {
        int offset = 0;
        int limit = 10;
        List<User> list = userDao.queryUsers(offset, limit);
        System.out.println("---queryUsers---"+list);
    }

}