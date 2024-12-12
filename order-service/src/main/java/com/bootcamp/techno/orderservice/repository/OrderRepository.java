package com.bootcamp.techno.orderservice.repository;

import com.bootcamp.techno.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
