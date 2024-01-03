package com.free.freelance_service.enums;

public enum ExceptionEnum {
    globalException("", "", "");
    private String en;
    private String ru;
    private String ua;

    ExceptionEnum(String en, String ru, String ua) {
        this.en = en;
        this.ru = ru;
        this.ua = ua;
    }
}
