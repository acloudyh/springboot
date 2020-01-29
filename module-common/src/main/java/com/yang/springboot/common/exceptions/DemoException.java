package com.yang.springboot.common.exceptions;

import lombok.Data;

/**
 * springboot demo 统一异常
 *
 * @author Yang Hao
 * @date 2020/1/12 17:40
 */
@Data
public class DemoException extends RuntimeException {

    private static final long serialVersionUID = -3576785908021342999L;

    private String error;

    public DemoException() {
        super();
    }

    public DemoException(String error) {
        super(error);
        this.error = error;
    }

    public DemoException(String error, String message) {
        super(message);
        this.error = error;
    }

    public DemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoException(Throwable cause) {
        super(cause);
    }

}
