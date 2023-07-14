package com.free.freelance_service.entity;

import com.free.freelance_service.entity.users.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@AttributeOverride(name = "id", column = @Column(name = "account_id"))
public class Account extends BaseEntity {
}
