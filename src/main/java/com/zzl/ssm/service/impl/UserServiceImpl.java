package com.zzl.ssm.service.impl;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.dao.UserMapper;
import com.zzl.ssm.dao.cache.UserRedisDao;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.IUserService;
import com.zzl.ssm.util.GlobalUtil;
import com.zzl.ssm.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private UserRedisDao userRedisDao;

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
        validResponse = this.checkValid(user.getEmail(), Constent.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setRole(Constent.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.successMsg("注册失败");
        }
        return ServerResponse.successMsg("注册成功");
    }

    /**
     * 校验用户名或邮箱
     *
     * @param str
     * @param type
     * @return
     */
    @Override
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

    @Override
    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse result = this.checkValid(username, Constent.USERNAME);
        if (result.isSuccess()) {
            return ServerResponse.errorMsg("用户名不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.successData(question);
        }
        return ServerResponse.errorMsg("找回密码的问题为空~");
    }

    @Override
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            String token = UUID.randomUUID().toString();
            userRedisDao.putKeyValue("token_" + username, token);
            return ServerResponse.successData(token);
        }
        return ServerResponse.errorMsg("问题的答案验证失败~");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String newPassword, String token) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(token)) {
            return ServerResponse.errorMsg("参数错误");
        }
        ServerResponse result = checkValid(username, Constent.USERNAME);
        if (result.isSuccess()) {
            return ServerResponse.errorMsg("用户名不存在");
        }
        String forgetToken = userRedisDao.getValueByKey("token_" + username);
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.errorMsg("token过期或者无效");
        }
        if (StringUtils.equals(token, forgetToken)) {
            String md5Pwd = MD5Util.MD5EncodeUtf8(newPassword);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Pwd);
            if (rowCount > 0) {
                return ServerResponse.successMsg("密码修改成功");
            }
        } else {
            return ServerResponse.errorMsg("token错误，请重新获取");
        }
        return ServerResponse.errorMsg("密码修改失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        if (StringUtils.isBlank(passwordOld) || StringUtils.isBlank(passwordNew)) {
            return ServerResponse.errorMsg("参数错误");
        }
        String md5pd = MD5Util.MD5EncodeUtf8(passwordOld);
        int resultCount = userMapper.checkPassword(user.getId(), md5pd);
        if (resultCount == 0) {
            return ServerResponse.errorMsg("当前密码错误");
        }
        user.setPassword(md5pd);
        resultCount = userMapper.updateByPrimaryKeySelective(user);
        if (resultCount > 0) {
            return ServerResponse.successMsg("密码更新成功");
        }
        return ServerResponse.errorMsg("密码更新失败");
    }

    @Override
    public ServerResponse<User> updateUserInfo(User user) {
        int resultCount = userMapper.checkEmailByUserId(user.getId(), user.getEmail());
        if (resultCount > 0) {
            return ServerResponse.errorMsg("邮箱已存在");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setAnswer(user.getAnswer());
        updateUser.setQuestion(user.getQuestion());

        resultCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (resultCount > 0) {
            return ServerResponse.success("更新成功", updateUser);
        }
        return ServerResponse.errorMsg("更新失败");
    }

    @Override
    public ServerResponse<User> getUserInfo(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.errorMsg("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.successData(user);
    }

    @Override
    public ServerResponse checkUserAdminRole(User user) {
        if (user != null && user.getRole() == Constent.Role.ROLE_ADMIN) {
            return ServerResponse.success();
        }
        return ServerResponse.error();
    }
}
