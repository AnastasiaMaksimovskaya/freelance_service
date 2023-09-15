package com.free.freelance_service.entity;

import com.free.freelance_service.entity.users.BaseEntity;
import com.free.freelance_service.enums.CurrencyEnum;
import com.free.freelance_service.enums.ThemeEnum;

import javax.persistence.*;

@Entity
@Table (name = "orders")
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
public class Order extends BaseEntity {

    @Column (name = "client_id")
    private String clientId;
    private String name;
    @Enumerated(EnumType.STRING)
    private ThemeEnum theme;
    private Long price;
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThemeEnum getTheme() {
        return theme;
    }

    public void setTheme(ThemeEnum theme) {
        this.theme = theme;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }
}
