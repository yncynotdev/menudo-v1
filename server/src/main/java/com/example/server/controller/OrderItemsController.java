package com.example.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.entity.OrderItems;
import com.example.server.service.OrderItemsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemsController {
  private final OrderItemsService orderItemsService;

  public OrderItemsController(OrderItemsService orderItemsService) {
    this.orderItemsService = orderItemsService;
  }

  @GetMapping
  public ResponseEntity<List<OrderItems>> getOrderItems() {
    return ResponseEntity.ok(orderItemsService.getOrderItems());
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderItems> getOrderItem(@PathVariable Integer id) {
    return ResponseEntity.ok(orderItemsService.getOrderItem(id));
  }

  @PostMapping
  public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItems orderItems) {
    return new ResponseEntity<>(orderItemsService.createOrderItem(orderItems), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<OrderItems> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItems orderItems) {
    return ResponseEntity.ok(orderItemsService.updateOrderItem(id, orderItems));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id) {
    orderItemsService.deleteOrderItem(id);
    return ResponseEntity.noContent().build();
  }

}
