package com.example.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.entity.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
}
