package com.zzl.ssm.service.impl;

import com.zzl.ssm.common.ServerResponse;
import com.zzl.ssm.dao.RnUploadMapper;
import com.zzl.ssm.entity.RnUpload;
import com.zzl.ssm.service.IRnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/6/9.
 */
@Service
public class RnServiceImpl implements IRnService {

    @Autowired
    private RnUploadMapper rnUploadMapper;

    @Override
    public ServerResponse add_rn(String username, Integer user_id, String appVname, Integer appVcode,
                                 String jsbundleName, String jsbundleUrl, Integer testNum, String describe) {
        RnUpload rnUpload = new RnUpload();
        rnUpload.setUsername(username);
        rnUpload.setUserid(user_id);
        rnUpload.setAppVname(appVname);
        rnUpload.setAppVcode(appVcode);
        rnUpload.setJsbundleName(jsbundleName);
        rnUpload.setJsbundleUrl(jsbundleUrl);
        rnUpload.setTestNum(testNum);
        rnUpload.setCreateTime(new Date());
        rnUpload.setStatus(0);
        rnUpload.setDescribe(describe);

        int rowCount = rnUploadMapper.insert(rnUpload);
        if (rowCount > 0) {
            return ServerResponse.successMsg("添加成功");
        }
        return ServerResponse.errorMsg("添加失败");
    }
}
