package com.zzl.ssm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class RnUpload {
    private Integer id;

    /**
     * 客户端versionname
     */
    private String appVname;

    /**
     * 客户端versioncode
     */
    private Integer appVcode;

    private String jsbundleName;

    private String jsbundleUrl;

    /**
     * 灰度测试数：小于等于0全部发布
     */
    private Integer testNum;

    /**
     * 状态：0待发布，1发布中，2暂停发布、3删除
     */
    private Integer status;

    private String username;

    private Integer userid;

    private Date createTime;

    private String describe;


    public RnUpload() {
    }

    public RnUpload(Integer id, String appVname, Integer appVcode, String jsbundleName, String jsbundleUrl, Integer testNum, Integer status, String username, Integer userid, Date createTime) {
        this.id = id;
        this.appVname = appVname;
        this.appVcode = appVcode;
        this.jsbundleName = jsbundleName;
        this.jsbundleUrl = jsbundleUrl;
        this.testNum = testNum;
        this.status = status;
        this.username = username;
        this.userid = userid;
        this.createTime = createTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppVname() {
        return appVname;
    }

    public void setAppVname(String appVname) {
        this.appVname = appVname;
    }

    public Integer getAppVcode() {
        return appVcode;
    }

    public void setAppVcode(Integer appVcode) {
        this.appVcode = appVcode;
    }

    public String getJsbundleName() {
        return jsbundleName;
    }

    public void setJsbundleName(String jsbundleName) {
        this.jsbundleName = jsbundleName;
    }

    public String getJsbundleUrl() {
        return jsbundleUrl;
    }

    public void setJsbundleUrl(String jsbundleUrl) {
        this.jsbundleUrl = jsbundleUrl;
    }

    public Integer getTestNum() {
        return testNum;
    }

    public void setTestNum(Integer testNum) {
        this.testNum = testNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}