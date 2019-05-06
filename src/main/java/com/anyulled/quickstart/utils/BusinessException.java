package com.anyulled.quickstart.utils;

import java.io.Serializable;

public class BusinessException extends Exception implements Serializable {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
