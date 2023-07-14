package com.free.freelance_service.entity.users;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
public class Client extends BaseUser {

}
