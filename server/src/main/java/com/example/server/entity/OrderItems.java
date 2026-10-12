package com.example.server.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItems {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Orders order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_id", nullable = false)
  private Dishes dishes;

  @Column(nullable = false)
  private Integer quantity;

  public OrderItems() {
  }

  public OrderItems(Orders order, Dishes dishes, Integer quantity) {
    this.order = order;
    this.dishes = dishes;
    this.quantity = quantity;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Orders getOrder() {
    return order;
  }

  public void setOrder(Orders order) {
    this.order = order;
  }

  public Dishes getDishes() {
    return dishes;
  }

  public void setDishes(Dishes dishes) {
    this.dishes = dishes;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
