package com.zzl.ssm.web;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/22.
 */
@Controller
@RequestMapping(value = "a/")
public class IndexController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "a", method = RequestMethod.GET)
    public String index() {
        return "test";
    }
}
