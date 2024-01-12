package com.free.freelance_service.security;

import com.free.freelance_service.entity.users.UserCredentials;
import com.free.freelance_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        UserCredentials credentials = userRepo.findFirstByLogin(username);
        if (credentials == null) {
            throw new BadCredentialsException("bad credentials");
        }
        if (!passwordEncoder.matches(password, credentials.getPassword())) {
            throw new BadCredentialsException("bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
