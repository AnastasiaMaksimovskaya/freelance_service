package com.free.freelance_service.factory;

import com.free.freelance_service.entity.TempUser;
import com.free.freelance_service.entity.users.BaseUser;
import com.free.freelance_service.entity.users.Performer;
import com.free.freelance_service.util.IdGeneratorUtil;

import java.util.Date;

public class PerformerFactory implements  UserFactory {
    @Override
    public BaseUser createUser() {
        return new Performer();
    }

    @Override
    public BaseUser createUser(TempUser dto) {
        Performer performer = new Performer();
        String id = IdGeneratorUtil.generate();
        performer.setId(id);
        performer.setUserId(dto.getUserId());
        performer.setCreated(new Date());
        performer.setUpdated(new Date());
        performer.setEmail(dto.getLogin());
        performer.setFirstName(dto.getFirstName());
        performer.setMiddleName(dto.getMiddleName());
        performer.setLastName(dto.getLastName());
        performer.setPhoneNumber(dto.getPhoneNumber());
        return performer;
    }
}
