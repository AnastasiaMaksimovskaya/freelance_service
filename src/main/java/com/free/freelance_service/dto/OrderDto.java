package com.free.freelance_service.dto;

import com.free.freelance_service.enums.CurrencyEnum;
import com.free.freelance_service.enums.ThemeEnum;

public class OrderDto {
    private String name;
    private String theme;
    private Long price;
    private String currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
