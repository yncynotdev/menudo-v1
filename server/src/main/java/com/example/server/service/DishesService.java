package com.example.server.service;

import com.example.server.entity.Dishes;
import com.example.server.repository.DishesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

  public Dishes createDish(Dishes dishes) {
    return dishesRepository.save(dishes);
  }

  public Dishes updateDish(Integer id, Dishes dishes) {
    Dishes existing = dishesRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not found"));

    if (dishes.getName() != null) {
      existing.setName(dishes.getName());
    }

    if (dishes.getPrice() != null) {
      existing.setPrice(dishes.getPrice());
    }

    existing.setImageKey(dishes.getImageKey());
    existing.setAvailable(dishes.getAvailable());

    return dishesRepository.save(existing);
  }

  public void deleteDish(Integer id) {
    Dishes dish = dishesRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Dish not found with id " + id));

    dishesRepository.delete(dish);
  }
}
