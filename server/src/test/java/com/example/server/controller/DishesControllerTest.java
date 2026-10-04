package com.example.server.controller;

import com.example.server.entity.Dishes;
import com.example.server.service.DishesService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest(DishesController.class)
public class DishesControllerTest {

  @MockitoBean
  private DishesService dishesService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldGetUsers() throws Exception {
    Dishes dish1 = new Dishes();
    dish1.setId(1);
    dish1.setName("Adobo");
    Dishes dish2 = new Dishes();
    dish2.setId(2);
    dish2.setName("Sinigang");

    when(dishesService.getDishes()).thenReturn(List.of(dish1, dish2));

    mockMvc.perform(get("/api/dishes")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2)).andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Adobo")).andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].name").value("Sinigang"));
  }

}
