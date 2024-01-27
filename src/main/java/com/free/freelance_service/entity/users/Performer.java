package com.free.freelance_service.entity.users;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "performer")
@AttributeOverride(name = "id", column = @Column(name = "performer_id"))
public class Performer extends BaseUser {
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
