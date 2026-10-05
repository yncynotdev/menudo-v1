package com.example.server.controller;

import com.example.server.entity.Dishes;
import com.example.server.service.DishesService;

import org.springframework.http.HttpStatus;
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
  public ResponseEntity<Dishes> createDish(@RequestBody Dishes dishes) {
    return new ResponseEntity<>(dishesService.createDish(dishes), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Dishes> updateDish(@PathVariable Integer id, @RequestBody Dishes dishes) {
    return ResponseEntity.ok(dishesService.updateDish(id, dishes));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Dishes> deleteDish(@PathVariable Integer id) {
    return new ResponseEntity<>(dishesService.deleteDish(id), HttpStatus.NO_CONTENT);
  }
}
