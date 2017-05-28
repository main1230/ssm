package com.zzl.ssm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/22.
 */
@Controller
@RequestMapping(value = "test/")
public class IndexController {

    @RequestMapping(value = "a/", method = RequestMethod.GET)
    public String index() {
        return "test";
    }
}
