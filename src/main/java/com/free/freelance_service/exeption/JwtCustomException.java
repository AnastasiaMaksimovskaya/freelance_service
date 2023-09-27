package com.free.freelance_service.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JwtCustomException extends AuthenticationException {

    private HttpStatus httpStatus;

    public JwtCustomException(String msg) {
        super(msg);
    }
    public JwtCustomException(String msg, HttpStatus status) {
        super(msg);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
