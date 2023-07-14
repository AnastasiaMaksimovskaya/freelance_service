package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.*;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.repo.ClientRepo;
import com.free.freelance_service.repo.PerformerRepo;
import com.free.freelance_service.repo.UserRepo;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PerformerRepo performerRepo;

//    @Transactional
    public void regUser (UserDto dto) {
        User user = new User();
        String userId = IdGeneratorUtil.generate();
        user.setId(userId);
        user.setLogin(dto.getLogin());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userRepo.save(user);
        if (dto.getRole().equals(RoleEnum.PERFORMER)) {
            createEntity(new Performer(), dto, userId);
        } else if (dto.getRole().equals(RoleEnum.CLIENT)) {
            createEntity(new Client(), dto, userId);
        }
    }
    private <T extends BaseUser> void createEntity(T entity, UserDto dto, String userId) {
        entity.setId(IdGeneratorUtil.generate());
        entity.setUserId(userId);
        entity.setCreated(new Date());
        entity.setUpdated(new Date());
        entity.setEmail(dto.getLogin());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        if (entity instanceof Client) {
            clientRepo.save((Client) entity);
        } else if (entity instanceof Performer) {
            performerRepo.save((Performer) entity);
        }
    }
}
