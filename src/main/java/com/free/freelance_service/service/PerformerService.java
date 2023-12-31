package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.Performer;
import com.free.freelance_service.entity.users.UserCredentials;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.repo.PerformerRepo;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerformerService {
    @Autowired
    private PerformerRepo performerRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Transactional
    public void regUser (UserDto dto) {
        String userId = IdGeneratorUtil.generate();
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserId(userId);
        userCredentials.setLogin(dto.getLogin());
        userCredentials.setPassword(passwordEncoder.encode(dto.getPassword()));
        userCredentials.setRole(RoleEnum.PERFORMER);
        Performer performer = new Performer();
        performer = userService.returnUser(performer, dto, userId);
        performerRepo.save(performer);
    }

    @Transactional
    public Performer performerInfo (String id) {
        return performerRepo.findById(id).get();
    }


}
