package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.Performer;
import com.free.freelance_service.repo.PerformerRepo;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
public class PerformerService {
    @Autowired
    private PerformerRepo performerRepo;

    @Transactional
    public void regUser (UserDto dto) {
        String userId = IdGeneratorUtil.generate();
        Performer performer = new Performer();
        performer.setId(userId);
        performer.setCreated(new Date());
        performer.setUpdated(new Date());
        performer.setEmail(dto.getLogin());
        performer.setFirstName(dto.getFirstName());
        performer.setMiddleName(dto.getMiddleName());
        performer.setLastName(dto.getLastName());
        performer.setPhoneNumber(dto.getPhoneNumber());
        performerRepo.save(performer);
    }
}
