package com.zzl.ssm.service;

import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/19.
 */
@Service
public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> forgetCheckAnswer(String username, String question, String answer);

    ServerResponse<String> forgetResetPassword(String username, String newPassword, String token);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateUserInfo(User user);

    ServerResponse<User> getUserInfo(Integer userId);

    /**
     * 是否是管理员
     * @param user
     * @return
     */
    ServerResponse checkUserAdminRole(User user);
}
