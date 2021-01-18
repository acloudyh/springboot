package com.yang.springboot.communication.result;

import lombok.Getter;

/**
 * @author Yang Hao
 * @date 2021-01-12 15:01
 */
public class ResultDto<T> {

    @Getter
    private Integer code = Code.SUCCESS;

    @Getter
    private T data;

    @Getter
    private String msg;

    public ResultDto() {
    }

    public ResultDto(T data) {
        this.data = data;
    }

    public ResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
