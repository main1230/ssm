package com.zzl.ssm.web;

import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2016/12/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list/{pageNum}/{pageSize}",
            method = RequestMethod.GET,
            produces = {Contens.json})
    public String list(@PathVariable("pageNum") Integer pageNum,
                       @PathVariable("pageSize") Integer pageSize,
                       Model model) {
        logger.debug("getUsers---", pageNum+"    " +pageSize);
        List<User> list = userService.getUsers(pageNum, pageSize);
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadPage() {
        return "file/fileUpload";
    }
}
