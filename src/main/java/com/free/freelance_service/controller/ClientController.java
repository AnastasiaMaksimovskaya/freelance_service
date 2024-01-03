package com.free.freelance_service.controller;

import com.free.freelance_service.dto.Message;
import com.free.freelance_service.dto.MessageResultDto;
import com.free.freelance_service.dto.UserDto;
import com.free.freelance_service.entity.users.Client;
import com.free.freelance_service.enums.StatusEnum;
import com.free.freelance_service.service.ClientService;
import com.free.freelance_service.service.ExchangeCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")

public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ExchangeCurrencyService exchangeCurrencyService;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Message registration (@RequestBody UserDto user) {
        Message message = new Message();
        clientService.regUser(user);
        message.setStatus(StatusEnum.ok.toString());
        return message;
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public MessageResultDto<Client> getInfo (@RequestParam String id) {
        MessageResultDto<Client> messageInfo = new MessageResultDto<>();
        Client clientInfo = clientService.clientInfo(id);
        messageInfo.setObject(clientInfo);
        return messageInfo;
    }

    @RequestMapping(value = "/getCurrency", method = RequestMethod.GET)
    public void getCurrency () {
        exchangeCurrencyService.getConvertedToUSD();
    }
}
