package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.Client;
import com.free.freelance_service.entity.users.UserCredentials;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.repo.ClientRepo;
import com.free.freelance_service.repo.UserRepo;
import com.free.freelance_service.security.JwtTokenProvider;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Transactional
    public String regUser (UserDto dto) {
        String userId = IdGeneratorUtil.generate();
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserId(userId);
        userCredentials.setLogin(dto.getLogin());
        userCredentials.setPassword(passwordEncoder.encode(dto.getPassword()));
        userCredentials.setRole(RoleEnum.CLIENT);
        userRepo.save(userCredentials);
        String token = jwtTokenProvider.createToken(dto.getLogin(), RoleEnum.CLIENT.toString(), userId);
        Client client = new Client();
        client = userService.returnUser(client, dto, userId);
        clientRepo.save(client);
        return token;
    }

    @Transactional
    public Client clientInfo (String id) {
        return clientRepo.findByUserId(id);
    }
}
