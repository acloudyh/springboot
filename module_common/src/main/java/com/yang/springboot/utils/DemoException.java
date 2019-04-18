package com.yang.springboot.utils;

import lombok.Data;

/**
 * @author yanghao
 * @date 2019-04-18 17:37
 */
@Data
public class DemoException extends RuntimeException {

    private static final long serialVersionUID = -6916719993363056158L;

    private String error;

    public DemoException() {
        super();
    }

    public DemoException(String error) {
        super(error);
        this.error = error;
    }

    public DemoException(String message, Throwable cause) {
        super(message, cause);
    }


}
