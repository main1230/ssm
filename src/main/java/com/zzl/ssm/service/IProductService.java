package com.zzl.ssm.service;

import com.github.pagehelper.PageInfo;
import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.entity.Product;
import com.zzl.ssm.vo.ProductDetailVO;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/29.
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVO> adminProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int PageSize);

    ServerResponse<PageInfo> productSearch(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVO> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);
}
