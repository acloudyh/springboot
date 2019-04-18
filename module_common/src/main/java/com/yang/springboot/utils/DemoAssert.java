package com.yang.springboot.utils;

/**
 * @author yanghao
 * @date 2019-04-18 17:40
 */
public class DemoAssert {

    private DemoAssert() {
    }


    /**
     * 对象为空，抛出异常
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new DemoException(message);
        }
    }

    /**
     * 对象不为空，抛出异常
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if(object != null) {
            throw new DemoException(message);
        }
    }

    /**
     * 表达式为假，抛出异常
     * @param expression
     * @param message
     */
    public static void isTrue(boolean expression, String message) {
        if(!expression) {
            throw new DemoException(message);
        }
    }
}
