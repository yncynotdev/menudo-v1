package com.example.server.service;

import com.example.server.entity.Dishes;
import com.example.server.repository.DishesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishesService {

  private final DishesRepository dishesRepository;

  public DishesService(DishesRepository dishesRepository) {
    this.dishesRepository = dishesRepository;
  }

  public List<Dishes> getDishes() {
    return dishesRepository.findAll();
  }

  public Dishes getDishes(Integer id) {
    return dishesRepository.findById(id)
        .orElseThrow();
  }

  public Dishes createDishes(Dishes dishes) {
    return dishesRepository.save(dishes);
  }

  public Dishes deleteDishes(Integer id) {
    Dishes dishes = dishesRepository.findById(id)
        .orElseThrow();
    dishesRepository.delete(dishes);
    return dishes;
  }
}
