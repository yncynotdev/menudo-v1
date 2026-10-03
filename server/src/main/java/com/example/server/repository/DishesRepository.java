package com.example.server.repository;

import com.example.server.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepository extends JpaRepository<Dishes, Long> { }