package com.yang.springboot.common.utils;

/**
 * @author yanghao
 * @date 2019-04-18 18:02
 */
public class SqlUtil {

    public static String wrapLike(String field) {
        DemoAssert.notNull(field, "field must not be null");
        StringBuilder sb = new StringBuilder(64);
        sb.append("%").append(field).append("%");
        return sb.toString();
    }

}
