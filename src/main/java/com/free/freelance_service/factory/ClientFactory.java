package com.free.freelance_service.factory;

import com.free.freelance_service.entity.TempUser;
import com.free.freelance_service.entity.users.BaseUser;
import com.free.freelance_service.entity.users.Client;
import com.free.freelance_service.util.IdGeneratorUtil;

import java.util.Date;

public class ClientFactory implements UserFactory {
    @Override
    public BaseUser createUser() {
        return new Client();
    }

    @Override
    public BaseUser createUser(TempUser dto) {
        Client client = new Client();
        String id = IdGeneratorUtil.generate();
        client.setId(id);
        client.setUserId(dto.getUserId());
        client.setCreated(new Date());
        client.setUpdated(new Date());
        client.setEmail(dto.getLogin());
        client.setFirstName(dto.getFirstName());
        client.setMiddleName(dto.getMiddleName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber(dto.getPhoneNumber());
        return client;
    }
}
