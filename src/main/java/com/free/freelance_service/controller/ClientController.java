package com.free.freelance_service.controller;

import com.free.freelance_service.dto.MessageResultDto;
import com.free.freelance_service.entity.users.Client;
import com.free.freelance_service.security.SecurityUtil;
import com.free.freelance_service.service.ClientService;
import com.free.freelance_service.service.ExchangeCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")

public class ClientController extends BaseController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ExchangeCurrencyService exchangeCurrencyService;


    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public MessageResultDto<Client> getInfo () {
        MessageResultDto<Client> messageInfo = new MessageResultDto<>();
        Client clientInfo = clientService.clientInfo(SecurityUtil.getUserId());
        messageInfo.setObject(clientInfo);
        return messageInfo;
    }

}
