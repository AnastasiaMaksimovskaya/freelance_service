package com.free.freelance_service.repo;

import com.free.freelance_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, String> , JpaSpecificationExecutor<Order> {

    @Query(value = "select * from orders LIMIT ?1 OFFSET ?2 order by ?3 ?4", nativeQuery = true)
    List<Order> findAllBySearch(int limit, int offset);
}
