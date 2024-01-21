package com.free.freelance_service.security;

import com.free.freelance_service.entity.users.UserCredentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuth implements Authentication {

    private Object credentials;
    private Object details;
    private final UserCredentials userCredentials;
    private boolean authenticated = false;

    public CustomAuth(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userCredentials.getRole().getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return this.details;
    }

    @Override
    public Object getPrincipal() {
        return userCredentials.getLogin();
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }
}
