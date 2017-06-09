package com.zzl.ssm.web;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.common.ServerResponseCode;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.IRnService;
import com.zzl.ssm.service.impl.RnServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/9.
 */
@Controller
@RequestMapping(value = "rn/")
public class RnController {

    @Autowired
    private IRnService iRnService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "page/add", method = RequestMethod.GET)
    public String rn_update() {
        return "rn_upload";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse rn_add(HttpSession session, @Param("appVname") String appVname, @Param("appVcode")Integer appVcode,
                                 @Param("jsbundleName")String jsbundleName,  @Param("jsbundleUrl")String jsbundleUrl,
                                 @Param("testNum")Integer testNum) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "需要登录");
        }
        ServerResponse response = iRnService.add_rn(user.getUsername(), user.getId(), appVname, appVcode, jsbundleName, jsbundleUrl, testNum);
        if (response.isSuccess()) {
            return ServerResponse.successMsg("上传成功");
        }
        return ServerResponse.errorMsg("上传失败");
    }
}
