package com.happytrip.domain.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public MemberException (String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
