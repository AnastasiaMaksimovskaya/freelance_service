package com.free.freelance_service.controller;

import com.free.freelance_service.dto.AuthDto;
import com.free.freelance_service.dto.Message;
import com.free.freelance_service.dto.MessageResultDto;
import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.BaseUser;
import com.free.freelance_service.entity.users.UserCredentials;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.enums.StatusEnum;
import com.free.freelance_service.security.JwtTokenProvider;
import com.free.freelance_service.security.SecurityUtil;
import com.free.freelance_service.service.ClientService;
import com.free.freelance_service.service.PerformerService;
import com.free.freelance_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class AuthController extends BaseController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PerformerService performerService;
    private final ClientService clientService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PerformerService performerService, ClientService clientService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.performerService = performerService;
        this.clientService = clientService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public <T extends BaseUser> MessageResultDto<T> authenticate(@RequestBody AuthDto authDto, HttpServletResponse httpServletResponse) {
        MessageResultDto<T> messageResultDto = new MessageResultDto<>();
        try {
            String login = authDto.getLogin();
            String password = authDto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            UserCredentials user = userService.findByLogin(login);
            String token = jwtTokenProvider.createToken(login, user.getRole().toString(), user.getUserId());
            T responseUser = null;
            switch (user.getRole()) {
                case PERFORMER -> {
                    responseUser = (T) performerService.performerInfo(user.getUserId());
                }
                case CLIENT -> {
                    responseUser = (T) clientService.clientInfo(user.getUserId());
                }
            }
            setJwtCookie(httpServletResponse, token);
            responseUser.setUserId(null);
            responseUser.setRole(user.getRole().toString());
            messageResultDto.setObject(responseUser);
            messageResultDto.setStatus(StatusEnum.ok.toString());
        } catch (AuthenticationException e) {
            messageResultDto.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            messageResultDto.setCode(HttpStatus.UNAUTHORIZED.value());
        }
        return messageResultDto;
    }

    @GetMapping("/getCurrent")
    public <T extends BaseUser> MessageResultDto<T> getCurrent() {
        MessageResultDto<T> messageResultDto = new MessageResultDto<>();
        try {
            try {
                String userId = SecurityUtil.getUserId();
                String role = SecurityUtil.getRole();
                T responseUser = null;
                switch (RoleEnum.valueOf(role)) {
                    case PERFORMER -> {
                        responseUser = (T) performerService.performerInfo(userId);
                    }
                    case CLIENT -> {
                        responseUser = (T) clientService.clientInfo(userId);
                    }
                }
                responseUser.setRole(role);
                messageResultDto.setObject(responseUser);
                messageResultDto.setStatus(StatusEnum.ok.toString());
            } catch (RuntimeException e) {
                return null;
            }

        } catch (AuthenticationException e) {
            messageResultDto.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            messageResultDto.setCode(HttpStatus.UNAUTHORIZED.value());
        }
        return messageResultDto;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        SecurityContextHolder.clearContext();
        Cookie deleted = new Cookie("jwt", null);
        deleted.setMaxAge(0);
        deleted.setPath("/");
        deleted.setSecure(true);
        deleted.setHttpOnly(true);
        response.addCookie(deleted);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Message registration (@RequestBody UserDto user) {
        Message message = new Message();
        userService.regUser(user);
        message.setStatus(StatusEnum.ok.toString());
        return message;
    }

    @RequestMapping(value = "/submitRegistration", method = RequestMethod.POST)
    public Message submitRegistration (@RequestBody UserDto user, HttpServletResponse response) {
        Message message = new Message();
        String token = userService.submitRegistration(user);
        setJwtCookie(response, token);
        message.setStatus(StatusEnum.ok.toString());
        return message;
    }
}
    