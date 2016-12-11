package com.zzl.ssm.service;
import com.zzl.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangzl
 * 描述：业务接口
 * 日期：  2016/12/5.
 */
public interface UserService {

    /**
     * 获取用户列表
     * @param offset
     * @param limit
     * @return
     */
    List<User> getUsers(int offset, int limit);

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    User getUserbyId(int id);

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    User login(String phone, String password);

    int register();

    /**
     * 删除用户
     * @param id  被删除的用户ID
     * @return
     */
    int deleteUserById(int id);

    /**
     * 更新用户信息
     * @param name
     * @param phone
     * @param password
     * @param headImage
     * @return
     */
    int updateUser(String name,
                   String phone,
                   String password,
                   String headImage);
}
