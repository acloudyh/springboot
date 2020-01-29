package com.yang.springboot.common.exceptions.error;

public enum ErrorEnum {

    METHOD_ARGUMENT_NOT_VALID("error.common.argumentNotValid", "方法参数无效"),
    ACCESS_DENIED("error.common.accessDenied", "进入拒绝"),
    INTERNAL_SERVER_ERROR("error.common.internalServerError", "服务内部错误"),
    ERR_METHOD_NOT_SUPPORTED("error.common.methodNotSupported", "方法不支持"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("error.common.mediaTypeNotSupported", "请求Content-Type不支持"),
    UN_PROCESSABLE_ENTITY("error.common.unProcessableEntity", "无法处理的实体类");

    private String error;
    private String desc;

    ErrorEnum(String error, String desc) {
        this.error = error;
        this.desc = desc;
    }

    public String getError() {
        return this.error;
    }

    public String getDesc() {
        return this.desc;
    }

}
