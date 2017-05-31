package com.zzl.ssm.web.backend;

import com.zzl.ssm.common.Constent;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.common.ServerResponseCode;
import com.zzl.ssm.entity.Product;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.service.IProductService;
import com.zzl.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/29.
 */
@Controller
@RequestMapping("admin/product/")
public class ProductAdminController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse saveProduct(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆");
        }
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iProductService.saveOrUpdateProduct(product);
        } else {
            return ServerResponse.errorMsg("无权操作");
        }
    }

    @RequestMapping(value = "set_sale_status", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId, Integer status) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆");
        }
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iProductService.setSaleStatus(productId, status);
        } else {
            return ServerResponse.errorMsg("无权操作");
        }
    }

    @RequestMapping(value = "detail/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, @PathVariable("productId") Integer productId) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆");
        }
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iProductService.adminProductDetail(productId);
        } else {
            return ServerResponse.errorMsg("无权操作");
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆");
        }
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iProductService.getProductList(pageNum, pageSize);
        } else {
            return ServerResponse.errorMsg("无权操作");
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse productSearch(HttpSession session, String productName, Integer productId,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Constent.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ServerResponseCode.NEED_LOGIN.getCode(), "用户未登陆");
        }
        if (iUserService.checkUserAdminRole(user).isSuccess()) {
            return iProductService.productSearch(productName, productId, pageNum, pageSize);
        } else {
            return ServerResponse.errorMsg("无权操作");
        }
    }
}
