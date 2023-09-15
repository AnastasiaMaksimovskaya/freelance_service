package com.free.freelance_service.controller;

import com.free.freelance_service.dto.Message;
import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.enums.StatusEnum;
import com.free.freelance_service.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Message registration (@RequestBody UserDto user) {
        Message message = new Message();
        clientService.regUser(user);
        message.setStatus(StatusEnum.ok.toString());
        return message;
    }
}
