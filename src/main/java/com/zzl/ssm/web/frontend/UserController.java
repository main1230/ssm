package com.zzl.ssm.web.frontend;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
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
            session.setAttribute(Constent.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resgiter(User user) {
        return iUserService.register(user);
    }
}
