package com.free.freelance_service.entity.users;

import com.free.freelance_service.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
public class UserCredentials {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
