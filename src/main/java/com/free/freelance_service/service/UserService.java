package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.*;
import com.free.freelance_service.enums.ExceptionEnum;
import com.free.freelance_service.repo.ClientRepo;
import com.free.freelance_service.repo.PerformerRepo;
import com.free.freelance_service.repo.UserRepo;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PerformerRepo performerRepo;

    public <T extends BaseUser> T returnNewUser(T baseUser, UserDto dto, String userId) {
        String id = IdGeneratorUtil.generate();
        baseUser.setId(id);
        baseUser.setUserId(userId);
        baseUser.setCreated(new Date());
        baseUser.setUpdated(new Date());
        baseUser.setEmail(dto.getLogin());
        baseUser.setFirstName(dto.getFirstName());
        baseUser.setMiddleName(dto.getMiddleName());
        baseUser.setLastName(dto.getLastName());
        baseUser.setPhoneNumber(dto.getPhoneNumber());
        return baseUser;
    }

    public UserCredentials findByLogin (String login) {
        UserCredentials firstByLogin = userRepo.findFirstByLogin(login);
        if (firstByLogin == null) {
            throw new RuntimeException(ExceptionEnum.authException.toString());
        }
        return firstByLogin;
    }
}
