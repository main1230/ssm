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
}
