package com.yang.springboot.communication.result;

/**
 * @author Yang Hao
 * @date 2021-01-12 15:01
 */
public class Code {

    public static final Integer SUCCESS = 200;

    public static final Integer UNSUPPORTED_MEDIA_TYPE = 415;

    /***************/
    /**  请求非法  **/
    /**  400系列  **/
    /**************/
    public static final Integer BAD_REQUEST = 400;

    /**
     * 同一个requestId多次被提交
     */
    public static final Integer MULTI_REQUEST = 40002;

    /***************/
    /**   鉴权非法 **/
    /**  401系列  **/
    /**************/
    public static final Integer UNAUTHORIZED = 401;

    /**
     * API凭据无效
     */
    public static final Integer BAD_AUTHENTICATION = 40101;

    /***************/
    /**  拒绝访问  **/
    /**  403系列  **/
    /**************/
    public static final Integer FORBIDDEN = 403;

    /***************/
    /** 资源未找到 **/
    /**  404系列  **/
    /**************/
    public static final Integer NOT_FOUND = 404;

    /***************/
    /**  业务错误  **/
    /**  422系列  **/
    /**************/
    public static final Integer UN_PROCESSABLE_ENTITY = 422;

    /***************/
    /**  系统错误  **/
    /**  500系列  **/
    /**************/
    public static final Integer INTERNAL_SERVER_ERROR = 500;

    public static final Integer SERVICE_INVOKE_ERROR = 50010;

    /**
     * 短信提交错误
     */
    public static final Integer SMS_SEND_ERROR = 50002;
}
