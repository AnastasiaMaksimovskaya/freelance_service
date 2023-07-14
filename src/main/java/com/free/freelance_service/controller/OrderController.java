package com.free.freelance_service.controller;


import com.free.freelance_service.dto.MessageResultDto;
import com.free.freelance_service.dto.OrderDto;
import com.free.freelance_service.entity.Order;
import com.free.freelance_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/addNewOrder", method = RequestMethod.POST)
    @ResponseBody
    public MessageResultDto<Order> addOrder (OrderDto orderDto) {
        MessageResultDto<Order> messageResultDto = new MessageResultDto<>();
        Order order = orderService.addNewOrder(orderDto);
        messageResultDto.setObject(order);
        return messageResultDto;
    }

    @RequestMapping(value = "/getOrders", method = RequestMethod.GET)
    public MessageResultDto<Order> getOrders() {
        MessageResultDto<Order> messageResultDto = new MessageResultDto<>();
        messageResultDto.setObjects(orderService.getAll());
        return messageResultDto;
    }
}
