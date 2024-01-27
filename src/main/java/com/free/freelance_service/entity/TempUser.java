package com.free.freelance_service.entity;

import com.free.freelance_service.enums.RoleEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "temp_user")
public class TempUser {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "confirmation_key")
    private String confirmationKey;
    @Column(name = "expires_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phoneNumber;
    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public TempUser() {
    }

    public TempUser(String userId, String confirmationKey, Date expiresAt) {
        this.userId = userId;
        this.confirmationKey = confirmationKey;
        this.expiresAt = expiresAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConfirmationKey() {
        return confirmationKey;
    }

    public void setConfirmationKey(String confirmationKey) {
        this.confirmationKey = confirmationKey;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
