package com.example.server.controller;

import com.example.server.entity.Dishes;
import com.example.server.service.DishesService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest(DishesController.class)
public class DishesControllerTest {

  @MockitoBean
  private DishesService dishesService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldGetDishes() throws Exception {
    Dishes dish1 = new Dishes();
    dish1.setId(1);
    dish1.setName("Adobo");
    Dishes dish2 = new Dishes();
    dish2.setId(2);
    dish2.setName("Sinigang");

    when(dishesService.getDishes()).thenReturn(List.of(dish1, dish2));

    mockMvc
        .perform(get("/api/dishes"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2)).andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Adobo")).andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].name").value("Sinigang"));
  }

  @Test
  void shouldGetDishesById() throws Exception {
    Dishes dish = new Dishes();

    dish.setId(1);
    dish.setName("Adobo");

    when(dishesService.getDishes(1)).thenReturn(dish);

    mockMvc
        .perform(get("/api/dishes/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Adobo"));
  }

  @Test
  void shouldCreateDish() throws Exception {
    Dishes dish = new Dishes();
    dish.setId(100);
    dish.setName("Ginataang Adobo");
    dish.setPrice(120F);

    when(dishesService.createDish(any(Dishes.class))).thenReturn(dish);

    mockMvc
        .perform(post("/api/dishes")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dish)))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(100))
        .andExpect(jsonPath("$.name").value("Ginataang Adobo")).andExpect(jsonPath("$.price").value(120F));

  }

  @Test
  void shouldUpdateDish() throws Exception {
    Dishes dish = new Dishes();
    dish.setId(1);
    dish.setName("Premium Adobo");
    dish.setPrice(150f);
    dish.setAvailable(false);

    when(dishesService.updateDish(eq(1), any(Dishes.class))).thenReturn(dish);

    mockMvc
        .perform(
            patch("/api/dishes/{id}", 1)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dish)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Premium Adobo"))
        .andExpect(jsonPath("$.price").value(150f))
        .andExpect(jsonPath("$.available").value(false));
  }

  @Test
  void shouldDeleteDish() throws Exception {
    mockMvc
        .perform(delete("/api/dishes/{id}", 1)).andExpect(status().isNoContent());

    verify(dishesService).deleteDish(1);
  }

}
