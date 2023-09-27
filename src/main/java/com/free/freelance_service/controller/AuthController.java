package com.free.freelance_service.controller;

import com.free.freelance_service.dto.AuthDto;
import com.free.freelance_service.dto.MessageResultDto;
import com.free.freelance_service.entity.users.UserCredentials;
import com.free.freelance_service.repo.UserRepo;
import com.free.freelance_service.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public MessageResultDto<?> authenticate(@RequestBody AuthDto authDto) {
        MessageResultDto messageResultDto = new MessageResultDto<>();
        try {
            String login = authDto.getLogin();
            String password = authDto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            UserCredentials user = userRepo.findFirstByLogin(login);
            String token = jwtTokenProvider.createToken(login, user.getRole().toString());
            Map<String, String> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);
            messageResultDto.setObject(response);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            messageResultDto.setStatus(HttpStatus.FORBIDDEN.getReasonPhrase());
            messageResultDto.setCode(HttpStatus.FORBIDDEN.value());
        }
        return messageResultDto;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
