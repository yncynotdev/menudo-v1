package com.example.server.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.server.entity.Orders;
import com.example.server.service.OrdersService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

  private final OrdersService ordersService;

  public OrdersController(OrdersService ordersService) {
    this.ordersService = ordersService;
  }

  @GetMapping
  public ResponseEntity<List<Orders>> getOrders() {
    return ResponseEntity.ok(ordersService.getOrders());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Orders> getOrderById(@PathVariable String id) {
    return ResponseEntity.ok(ordersService.getOrderById(id));
  }

  @PostMapping
  public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
    return new ResponseEntity<>(ordersService.createOrder(order), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Orders> updateOrder(@PathVariable String id, @RequestBody Orders order) {
    return ResponseEntity.ok(ordersService.updateOrder(id, order));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
    ordersService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
