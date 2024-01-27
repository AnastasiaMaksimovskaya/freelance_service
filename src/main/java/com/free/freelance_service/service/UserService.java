package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.TempUser;
import com.free.freelance_service.entity.users.*;
import com.free.freelance_service.enums.ExceptionEnum;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.factory.ClientFactory;
import com.free.freelance_service.factory.PerformerFactory;
import com.free.freelance_service.factory.UserFactory;
import com.free.freelance_service.repo.ClientRepo;
import com.free.freelance_service.repo.PerformerRepo;
import com.free.freelance_service.repo.TempUserRepo;
import com.free.freelance_service.repo.UserRepo;
import com.free.freelance_service.security.JwtTokenProvider;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TempUserRepo tempUserRepo;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PerformerRepo performerRepo;

    @Transactional
    public String regUser (UserDto dto) {
        String confirmationKey = IdGeneratorUtil.generate();
        String userId = IdGeneratorUtil.generate();
        TempUser user = convertAuthDtoToTempUser(dto);
        user.setUserId(userId);
        user.setConfirmationKey(confirmationKey);
        user.setExpiresAt(Date.from(new Date().toInstant().plusSeconds(300)));
        tempUserRepo.save(user);
        return emailSenderService.sendConfirmRegistration(dto.getLogin(), confirmationKey, userId, dto.getFirstName(), dto.getRole().toString().toLowerCase());
    }
    @Transactional
    public String submitRegistration (UserDto userDto) {
        String userId = userDto.getUserId();
        TempUser temp = tempUserRepo.findById(userId).get();
        if (!passwordEncoder.matches(userDto.getPassword(), temp.getPassword()) || !userDto.getLogin().equals(temp.getLogin())) {
            throw new BadCredentialsException("bad credentials");
        }
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserId(userId);
        userCredentials.setLogin(temp.getLogin());
        userCredentials.setPassword(temp.getPassword());
        userCredentials.setRole(temp.getRole());
        String token = jwtTokenProvider.createToken(temp.getLogin(), temp.getRole().toString(), userId);
        BaseUser user = returnNewUser(temp);
        saveUser(user, temp.getRole());
        tempUserRepo.delete(temp);
        return token;
    }

    public BaseUser returnNewUser(TempUser dto) {
        UserFactory userFactory = getUserFactory(dto.getRole());
        return userFactory.createUser(dto);
    }

    public UserCredentials findByLogin (String login) {
        UserCredentials firstByLogin = userRepo.findFirstByLogin(login);
        if (firstByLogin == null) {
            throw new RuntimeException(ExceptionEnum.authException.toString());
        }
        return firstByLogin;
    }

    public TempUser convertAuthDtoToTempUser (UserDto dto) {
        TempUser user = new TempUser();
        user.setLogin(dto.getLogin());
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        return user;
    }

    private UserFactory getUserFactory (RoleEnum role) {
        switch (role) {
            case PERFORMER -> {
                return new PerformerFactory();
            }
            case CLIENT -> {
                return new ClientFactory();
            }
            default -> {
                throw new RuntimeException("role not found");
            }
        }
    }

    private void saveUser (BaseUser user, RoleEnum role) {
        switch (role) {
            case PERFORMER -> {
                performerRepo.save((Performer) user);
            }
            case CLIENT -> {
                clientRepo.save((Client) user);
            }
            default -> {
                throw new RuntimeException("role not found");
            }
        }
    }
}
