package com.free.freelance_service.entity;

import com.free.freelance_service.enums.CurrencyEnum;

import javax.persistence.*;

@Entity
@Table(name = "exchange")
public class ExchangeCurrency {
    @Id
    private String currency;
    private Double units;

    public ExchangeCurrency(String currency, Double units) {
        this.currency = currency;
        this.units = units;
    }

    public ExchangeCurrency() {

    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }
}
