package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.TempUser;
import com.free.freelance_service.entity.users.BaseUser;
import com.free.freelance_service.entity.users.Performer;
import com.free.freelance_service.entity.users.UserCredentials;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.repo.TempUserRepo;
import com.free.freelance_service.repo.PerformerRepo;
import com.free.freelance_service.security.JwtTokenProvider;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PerformerService {
    @Autowired
    private PerformerRepo performerRepo;


    @Transactional
    public Performer performerInfo (String id) {
        return performerRepo.findByUserId(id);
    }


}
