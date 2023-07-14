package com.free.freelance_service.service;

import com.free.freelance_service.dto.OrderDto;
import com.free.freelance_service.entity.Order;
import com.free.freelance_service.entity.users.Performer;
import com.free.freelance_service.enums.CurrencyEnum;
import com.free.freelance_service.enums.ThemeEnum;
import com.free.freelance_service.repo.OrderRepo;
import com.free.freelance_service.util.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    public Order addNewOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(IdGeneratorUtil.generate());
        order.setCreated(new Date());
        order.setUpdated(new Date());
        order.setName(orderDto.getName());
        order.setCurrency(CurrencyEnum.valueOf(orderDto.getCurrency()));
        order.setTheme(ThemeEnum.valueOf(orderDto.getTheme()));
        return orderRepo.save(order);
    }

    public List<Order> getAll() {
        return orderRepo.findAll();
    }
}
