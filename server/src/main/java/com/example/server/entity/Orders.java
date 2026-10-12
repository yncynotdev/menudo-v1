package com.example.server.entity;

import com.example.server.enums.orders.Status;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {

  @Id
  @Column(length = 36)
  private String id;

  @Column
  private Integer orderNumber;

  @Column(nullable = false)
  private String createdAt;

  @Column(nullable = false)
  private String customerName;

  @Column(nullable = false)
  private Integer tableNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, columnDefinition = "ENUM('PENDING', 'PREPARING', 'SERVED', 'PAID', 'CANCELLED') DEFAULT 'PENDING'")
  private Status status = Status.PENDING;

  @Column(nullable = false)
  private Float totalAmount;

  public Orders() {
  }

  public Orders(String id, Integer orderNumber, String createdAt, String customerName, Integer tableNumber,
      Status status,
      Float totalAmount) {
    this.id = id;
    this.orderNumber = orderNumber;
    this.createdAt = createdAt;
    this.customerName = customerName;
    this.tableNumber = tableNumber;
    this.status = status;
    this.totalAmount = totalAmount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public Integer getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(Integer tableNumber) {
    this.tableNumber = tableNumber;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Float getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Float totalAmount) {
    this.totalAmount = totalAmount;
  }
}
