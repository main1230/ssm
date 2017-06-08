package com.zzl.ssm.service;

import com.zzl.ssm.common.ServerResponse;
import org.springframework.stereotype.Service;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/3.
 */
@Service
public interface ICartService {

    ServerResponse addCart(Integer userId, Integer productId, Integer count);
}
