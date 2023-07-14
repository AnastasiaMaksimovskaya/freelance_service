package com.free.freelance_service.entity.users;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Table(name = "users")
public class User extends BaseEntity {
    private String login;
    private String password;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
