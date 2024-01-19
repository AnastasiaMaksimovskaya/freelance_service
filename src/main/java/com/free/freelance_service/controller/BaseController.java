package com.free.freelance_service.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseController {

    public void setJwtCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt", token);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
