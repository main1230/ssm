package com.zzl.ssm.dao.cache;

import com.zzl.ssm.dao.UserMapper;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.util.GlobalUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserRedisDaoTest {

    private int id = 1;

    @Autowired
    private UserRedisDao userRedisDao;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser() throws Exception {
        String key = GlobalUtil.getUUID(1);
        System.out.println("key=" + key);
        User user = userRedisDao.getUserByKey(key);
        if (user == null) {
            user = userMapper.selectByPrimaryKey(id);
            if (user != null) {
                String s = userRedisDao.putUser(key, user);
                System.out.println(s);
                user = userRedisDao.getUserByKey(key);
                System.out.println("key->user=" + user);
            } else {
                System.out.println("不存在");
            }
        } else {
            System.out.println("已存在" + user);
        }
    }

    @Test
    public void testCommonUser() throws Exception {
        String key = "kc";
        String content = "哈哈，字符串试试了";
        System.out.println("init   key=" + key + "  content=" + content);
        String r_c = userRedisDao.getValueByKey(key);
        if (r_c == null || r_c.trim().equals("")) {
            String rr = userRedisDao.putKeyValue(key, content);
            System.out.println("字符串key值不存在，保存  rr=" + rr);
        } else {
            System.out.println("字符串key值存在：" + r_c);
        }

        String user_key = GlobalUtil.getUUID(1);
        System.out.println("user_key=" + user_key);
        User user = userRedisDao.getUserByKey(user_key);
        if (user == null) {
            user = userMapper.selectByPrimaryKey(id);
            if (user != null) {
                String uu = userRedisDao.putKeyValue(user_key, user);
                System.out.println("内存中没有，存储，uu=" + uu);
                user = userRedisDao.getUserByKey(user_key);
                System.out.println("内存里的user=" + user);
            } else {
                System.out.println("user数据库也不存在");
            }
        } else {
            System.out.println("user存在：" + user);
        }
    }
}