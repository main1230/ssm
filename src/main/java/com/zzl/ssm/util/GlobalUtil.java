package com.zzl.ssm.util;

/**
 * Created by zhangzl
 * 描述：
 * 日期：  2017/5/28.
 */
public class GlobalUtil {

    /**
     * 生成24位UUID
     *
     * @return UUID 24bit string
     */
    public static String getUUID(int id) {
        long time = System.currentTimeMillis();

        String uuid = "Z" + Long.toHexString(time) + Integer.toHexString(id)
                + Long.toHexString(Double.doubleToLongBits(Math.random()));

        uuid = uuid.substring(0, 24).toUpperCase();

        return uuid;
    }
}
