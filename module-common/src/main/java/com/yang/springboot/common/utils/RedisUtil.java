package com.yang.springboot.common.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author yanghao
 * @date 2019-04-22 16:15
 */
public class RedisUtil {

    /**
     * 默认过期时长
     */
    public static final long DEFAULT_EXPIRE = 60L;
    private static String REDIS_KEY = "REDIS_KEY_%s";

    /**
     * 生成key
     *
     * @param billCode
     * @return
     */
    public static String waybillKey(String billCode) {
        return String.format(REDIS_KEY, billCode);
    }


    /**
     * 保存redis
     *
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     * @param redisTemplate
     */
    public static void valueAdd(String key, Object value, boolean expire, TimeUnit timeUnit, RedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(key, value);
        if (expire) {
            redisTemplate.expire(key, DEFAULT_EXPIRE, timeUnit);
        }
    }


    /**
     * 根据key删除redis
     *
     * @param key
     * @param redisTemplate
     */
    public static void valueDelete(String key, RedisTemplate redisTemplate) {
        redisTemplate.delete(key);
    }


    /**
     * 根据key获取值
     *
     * @param key
     * @param redisTemplate
     * @param <T>
     * @return
     */
    public static <T> T valueGet(String key, RedisTemplate redisTemplate) {
        Object object = redisTemplate.opsForValue().get(key);
        if (null != object) {
            return (T) object;
        }
        return null;
    }

}
