package com.zzl.ssm.web.app;

import com.zzl.ssm.dto.Result;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.UserService;
import com.zzl.ssm.web.Contens;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2016/12/6.
 */
@Controller
@RequestMapping(value = "/app/user")
public class AppUserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list/{pageNum}/{pageSize}",
            method = RequestMethod.GET,
            produces = {Contens.json})
    @ResponseBody
    public Result list(@PathVariable("pageNum") Integer pageNum,
                       @PathVariable("pageSize") Integer pageSize) {
        logger.debug("getUsers---"+ pageNum+"    pageSize=" +pageSize);
        System.out.println(pageNum+"-----------------------");
        Result result = new Result();
        try {
            List<User> list = userService.getUsers(pageNum, pageSize);
            result.setCode(1);
            result.setMsg("查询成功");
            result.setData(list);
        } catch (Exception e) {
            logger.error("---getUsers---", e.getMessage());
            e.printStackTrace();
            result.setCode(2);
            result.setMsg("查询失败");
        }
        return result;
    }
}
