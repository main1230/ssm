package com.zzl.ssm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/9.
 */
@Controller
@RequestMapping("rn/")
public class RnController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "manager", method = RequestMethod.GET)
    public String rn_update() {
        return "rn_upload";
    }
}
