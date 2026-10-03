package com.example.server.controller;

import com.example.server.entity.Dishes;
import com.example.server.service.DishesService;
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
    public List<Dishes> getDishes() {
        return dishesService.getDishes();
    }

    @GetMapping("/{id}")
    public Dishes getDishes(@PathVariable Long id) {
        return dishesService.getDishes(id);
    }

    @PostMapping
    public Dishes addDishes(@RequestBody Dishes dishes) {
        return dishesService.createDishes(dishes);
    }

    @DeleteMapping("/{id}")
    public Dishes deleteDishes(@PathVariable Long id) {
        return dishesService.deleteDishes(id);
    }
}