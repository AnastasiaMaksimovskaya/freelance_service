package com.free.freelance_service.controller;

import com.free.freelance_service.dto.Message;
import com.free.freelance_service.dto.MessageResultDto;
import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.Performer;
import com.free.freelance_service.enums.StatusEnum;
import com.free.freelance_service.security.SecurityUtil;
import com.free.freelance_service.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/performer")
@CrossOrigin(origins = "*")
public class PerformerController extends BaseController{

    @Autowired
    private PerformerService performerService;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Message registration (@RequestBody UserDto user, HttpServletResponse response) {
        Message message = new Message();
        String token = performerService.regUser(user);
        setJwtCookie(response, token);
        message.setStatus(StatusEnum.ok.toString());
        return message;
    }
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public MessageResultDto<Performer> getInfo () {
        MessageResultDto<Performer> messageInfo = new MessageResultDto<>();
        Performer performerInfo = performerService.performerInfo(SecurityUtil.getUserId());
        messageInfo.setObject(performerInfo);
        return messageInfo;
    }
}
