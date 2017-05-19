package com.zzl.ssm.common;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/19.
 */
public class Constent {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
