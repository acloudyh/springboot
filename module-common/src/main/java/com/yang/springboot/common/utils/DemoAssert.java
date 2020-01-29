package com.yang.springboot.common.utils;


import com.yang.springboot.common.exceptions.DemoException;

/**
 * springboot demo 断言工具类
 *
 * @author Yang Hao
 * @date 2020/1/29 21:36
 */
public class DemoAssert {

    private DemoAssert() {
    }

    /**
     * 空 报异常
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new DemoException(message);
        }
    }

    /**
     * 非空 报异常
     *
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new DemoException(message);
        }
    }

    /**
     * 假 报异常
     *
     * @param expression
     * @param message
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new DemoException(message);
        }
    }

    /**
     * 真 报异常
     *
     * @param expression
     * @param message
     */
    public static void notTrue(boolean expression, String message) {
        if (expression) {
            throw new DemoException(message);
        }
    }
}

