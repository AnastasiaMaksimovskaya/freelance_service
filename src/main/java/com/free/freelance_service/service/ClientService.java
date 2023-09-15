package com.free.freelance_service.service;

import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.BaseUser;
import com.free.freelance_service.entity.users.Client;
import com.free.freelance_service.entity.users.Performer;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.repo.ClientRepo;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;
    
    @Transactional
    public void regUser (UserDto dto) {
        String userId = IdGeneratorUtil.generate();
        Client client = new Client();
        client.setId(userId);
        client.setLogin(dto.getLogin());
        client.setPassword(dto.getPassword());
        client.setCreated(new Date());
        client.setUpdated(new Date());
        client.setEmail(dto.getLogin());
        client.setFirstName(dto.getFirstName());
        client.setMiddleName(dto.getMiddleName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber(dto.getPhoneNumber());
        clientRepo.save(client);
    }
}
