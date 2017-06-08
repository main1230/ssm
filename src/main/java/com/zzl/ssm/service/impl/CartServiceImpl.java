package com.zzl.ssm.service.impl;

import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.dao.CartMapper;
import com.zzl.ssm.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/3.
 */
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public ServerResponse addCart(Integer userId, Integer productId, Integer count) {

        return null;
    }
}
