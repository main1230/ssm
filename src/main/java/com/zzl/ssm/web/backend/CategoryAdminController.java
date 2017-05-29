package com.zzl.ssm.web.backend;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.common.ServerResponseCode;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.ICategoryService;
import com.zzl.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/29.
 */
@RequestMapping(value = "admin/category/")
@Controller
public class CategoryAdminController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "add_category", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName,
                                      @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        // 校验是否是管理员
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iCategoryService.addCategory(categoryName, parentId);
        } else {
            return ServerResponse.errorMsg("权限不够，需要管理员权限");
        }
    }

    @RequestMapping(value = "set_category_name", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, String categoryName, Integer categoryId) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        // 校验是否是管理员
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iCategoryService.updateCategoryName(categoryName, categoryId);
        } else {
            return ServerResponse.errorMsg("权限不够，需要管理员权限");
        }
    }

    @RequestMapping(value = "get_category", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,
                                                      @RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        // 校验是否是管理员
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iCategoryService.getChildrenParallelCategory(categoryId);
        } else {
            return ServerResponse.errorMsg("权限不够，需要管理员权限");
        }
    }

    @RequestMapping(value = "get_deep_category", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,
                                                      @RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        // 校验是否是管理员
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        } else {
            return ServerResponse.errorMsg("权限不够，需要管理员权限");
        }
    }
}
