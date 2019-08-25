package com.thought.it.exception;

/**
 * Created by Administrator on 2019/8/25.
 */
public class CustomizeException extends RuntimeException {
    private  String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
