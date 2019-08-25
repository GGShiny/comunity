package com.thought.it.exception;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2019/8/25.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("问题不存在，请换个试试！！");

    private String message;

    @Autowired
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
