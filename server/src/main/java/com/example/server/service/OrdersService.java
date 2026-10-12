package com.example.server.service;

import com.example.server.entity.Orders;
import com.example.server.repository.OrdersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class OrdersService {

  private final OrdersRepository ordersRepository;

  public OrdersService(OrdersRepository ordersRepository) {
    this.ordersRepository = ordersRepository;
  }

  public List<Orders> getOrders() {
    return ordersRepository.findAll();
  }

  public Orders getOrderById(String id) {
    return ordersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Order not found with id " + id));
  }

  public Orders createOrder(Orders orders) {
    return ordersRepository.save(orders);
  }

  public Orders updateOrder(String id, Orders orders) {
    Orders order = ordersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Order not found with id " + id));

    return ordersRepository.save(order);
  }

  public void deleteOrder(String id) {
    Orders order = ordersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Order not found with id " + id));

    ordersRepository.delete(order);
  }
}
