package com.zzl.ssm.web.frontend;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.common.ServerResponseCode;
import com.zzl.ssm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/3.
 */
@Controller
@RequestMapping("car/")
public class CarController {

//    public ServerResponse addCar(HttpSession session, Integer count, Integer productId) {
//        User user = (User) session.getAttribute(Constent.CURRENT_USER);
//        if (user == null) {
//            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "登录超时，请重新登录");
//        }
//
//    }
}
