package com.free.freelance_service.security;

import com.free.freelance_service.entity.users.BaseUser;
import com.free.freelance_service.entity.users.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private final String userName;
    private final String password;
    private final boolean isActive;
    private final String role;

    public UserDetailsImpl(String userName, String password, boolean isActive, String role) {
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUserCredentials (UserCredentials user)  {
        return new User(user.getLogin(), user.getPassword(), user.getRole().getAuthorities());
    }
}
