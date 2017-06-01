package com.zzl.ssm.web.frontend;

import com.github.pagehelper.PageInfo;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.service.IProductService;
import com.zzl.ssm.vo.ProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/1.
 */
@RequestMapping("product/")
@Controller
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "detail/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVO> detail(@PathVariable("productId") Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }
}
