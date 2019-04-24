package com.yang.springboot.common.utils;

import org.springframework.beans.BeanUtils;

/**
 * dto和domain 类型之间转换
 *
 * @author yanghao
 * @date 2019-04-18 17:04
 */
public class ModelConvert {

    public static <T> T to(Object source, Class<T> target) {
        T t = null;
        try {
            if (source != null) {
                t = target.newInstance();
                BeanUtils.copyProperties(source, t);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;

    }

}
