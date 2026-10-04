package com.example.server.controller;

import com.example.server.entity.Dishes;
import com.example.server.service.DishesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishesController {
  private final DishesService dishesService;

  public DishesController(DishesService dishesService) {
    this.dishesService = dishesService;
  }

  @GetMapping
  public ResponseEntity<List<Dishes>> getDishes() {
    return ResponseEntity.ok(dishesService.getDishes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Dishes> getDishes(@PathVariable Integer id) {
    return ResponseEntity.ok(dishesService.getDishes(id));
  }

  @PostMapping
  public ResponseEntity<Dishes> addDishes(@RequestBody Dishes dishes) {
    return new ResponseEntity<>(dishesService.createDishes(dishes), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Dishes> deleteDishes(@PathVariable Integer id) {
    return new ResponseEntity<>(dishesService.deleteDishes(id), HttpStatusCode.valueOf(204));
  }
}
