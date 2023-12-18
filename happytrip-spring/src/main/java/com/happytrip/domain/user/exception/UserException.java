package com.happytrip.domain.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public UserException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
