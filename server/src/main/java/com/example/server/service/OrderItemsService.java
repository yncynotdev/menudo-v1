package com.example.server.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.server.entity.OrderItems;
import com.example.server.repository.OrderItemsRepository;

import java.util.List;

@Service
public class OrderItemsService {
  private final OrderItemsRepository orderItemsRepository;

  public OrderItemsService(OrderItemsRepository orderItemsRepository) {
    this.orderItemsRepository = orderItemsRepository;
  }

  public List<OrderItems> getOrderItems() {
    return orderItemsRepository.findAll();
  }

  public OrderItems getOrderItem(Integer id) {
    return orderItemsRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordered item not found " + id));
  }

  public OrderItems createOrderItem(OrderItems orderItems) {
    return orderItemsRepository.save(orderItems);
  }

  public OrderItems updateOrderItem(Integer id, OrderItems orderItems) {
    OrderItems existing = orderItemsRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordered item not found " + id));

    existing.setDishes(orderItems.getDishes());
    existing.setOrder(orderItems.getOrder());
    existing.setQuantity(orderItems.getQuantity());

    return orderItemsRepository.save(orderItems);
  }

  public void deleteOrderItem(Integer id) {
    OrderItems existing = orderItemsRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordered item not found " + id));

    orderItemsRepository.delete(existing);
  }
}
