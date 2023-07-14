package com.free.freelance_service.repo;

import com.free.freelance_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, String> {
}
