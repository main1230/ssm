package com.zzl.ssm.dao;

import com.zzl.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2016/12/4.
 */
public interface UserDao {
    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 登录
     * @param phone  手机号
     * @param password  密码
     * @return
     */
    User login(@Param("phone") String phone, @Param("password") String password);

    /**
     * 用户注册
     * @param name
     * @param phone
     * @param password
     * @return
     */
    int register(@Param("name") String name, @Param("phone") String phone, @Param("password") String password);

    /**
     * 删除用户
     * @param id 用户ID
     * @return
     */
    int deleteUserById(int id);

    /**
     * 获取用户列表
     * @param offset
     * @param limit
     * @return
     */
    List<User> queryUsers(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 更新用户信息
     * @param name
     * @param phone
     * @param password
     * @param headImage
     * @return
     */
    int updateUser(@Param("name") String name,
                   @Param("phone") String phone,
                   @Param("password") String password,
                   @Param("headImage") String headImage);
}
