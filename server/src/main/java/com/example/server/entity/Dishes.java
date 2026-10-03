package com.example.server.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dishes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Float price;

  private Boolean isAvailable;

  private String imageKey;

  public Dishes() {}

  public Dishes(String id, String name, Float price, Boolean isAvailable, String imageKey) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.isAvailable = isAvailable;
    this.imageKey = imageKey;
  }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
