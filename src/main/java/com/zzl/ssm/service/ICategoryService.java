package com.zzl.ssm.service;

import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.entity.Category;

import java.util.List;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/29.
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(String categoryName, Integer categoryId);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 递归查询本节点及子节点的ID
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
