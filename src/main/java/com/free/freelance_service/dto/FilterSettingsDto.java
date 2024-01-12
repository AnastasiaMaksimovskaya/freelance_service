package com.free.freelance_service.dto;

import com.free.freelance_service.enums.ThemeEnum;

import java.util.List;

public class FilterSettingsDto {
    int minPrice;
    int maxPrice;
    List<ThemeEnum> themes;

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<ThemeEnum> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeEnum> themes) {
        this.themes = themes;
    }
}
