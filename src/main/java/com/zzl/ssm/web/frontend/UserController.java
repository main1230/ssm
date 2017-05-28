package com.zzl.ssm.web.frontend;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.common.ServerResponseCode;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/19.
 */
@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Constent.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Constent.CURRENT_USER);
        return ServerResponse.successMsg("已退出");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resgiter(User user) {
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    @RequestMapping(value = "get_user_info", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user != null) {
            return ServerResponse.successData(user);
        }
        return ServerResponse.errorMsg("用户未登陆");
    }

    @RequestMapping(value = "forget_get_question", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }

    @RequestMapping(value = "forget_check_answer", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.forgetCheckAnswer(username, question, answer);
    }

    @RequestMapping(value = "forget_reset_password", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username, String newPassword, String token) {
        return iUserService.forgetResetPassword(username, newPassword, token);
    }

    @RequestMapping(value = "reset_password", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.errorMsg("用户未登陆");
        }
        return iUserService.resetPassword(passwordOld, passwordNew, user);
    }

    @RequestMapping(value = "update_user_info", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInfo(HttpSession session, User user) {
        User curUser = (User) session.getAttribute(Constent.CURRENT_USER);
        if (curUser == null) {
            return ServerResponse.errorMsg("用户未登陆");
        }
        user.setId(curUser.getId());
        user.setUsername(curUser.getUsername());
        ServerResponse result = iUserService.updateUserInfo(user);
        if (result.isSuccess()) {
            session.setAttribute(Constent.CURRENT_USER, result.getData());
        }
        return result;
    }

    @RequestMapping(value = "get_information", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆");
        }
        return iUserService.getUserInfo(user.getId());
    }
}
