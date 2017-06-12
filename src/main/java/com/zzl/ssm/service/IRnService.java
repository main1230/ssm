package com.zzl.ssm.service;

import com.zzl.ssm.common.ServerResponse;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/9.
 */
public interface IRnService {

    ServerResponse add_rn(String username, Integer user_id, String appVname, Integer appVcode,
                                 String jsbundleName, String jsbundleUrl, Integer testNum, String describe);
}
