package com.free.freelance_service.security;

import com.free.freelance_service.enums.ExceptionEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public final class SecurityUtil {
    private SecurityUtil() {
    }

    public static CustomAuth getAuthentication () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuth) {
            return (CustomAuth) authentication;
        }
        throw new RuntimeException(ExceptionEnum.authException.toString());
    }

    public static String getRole() {
        return getAuthentication().getUserCredentials().getRole().toString();
    }

    public static String getUserId () {
        return getAuthentication().getUserCredentials().getUserId();
    }

}
