package com.free.freelance_service.controller;

import com.free.freelance_service.dto.Message;
import com.free.freelance_service.dto.MessageResultDto;
import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.BaseEntity;
import com.free.freelance_service.enums.RoleEnum;
import com.free.freelance_service.enums.StatusEnum;
import com.free.freelance_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Message registration (@RequestBody UserDto user) {
        Message message = new Message();
        userService.regUser(user);
        message.setStatus(StatusEnum.ok.toString());
        return message;
    }
}
