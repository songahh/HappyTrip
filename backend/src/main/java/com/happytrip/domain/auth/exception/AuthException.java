package com.happytrip.domain.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public AuthException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
