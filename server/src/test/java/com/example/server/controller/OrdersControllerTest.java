package com.example.server.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.server.entity.Orders;
import com.example.server.enums.orders.Status;
import com.example.server.service.OrdersService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(OrdersController.class)
public class OrdersControllerTest {

  @MockitoBean
  private OrdersService ordersService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldGetOrders() throws Exception {
    Orders order1 = new Orders("menudo-0001", 1000, "2026-10-05 14:51:46",
        "Suki", 10, Status.PENDING, 1000.0f);
    Orders order2 = new Orders("menudo-0002", 1001, "2026-10-05 14:51:46",
        "Aaron", 11, Status.PREPARING, 1200.0f);

    when(ordersService.getOrders()).thenReturn(List.of(order1, order2));

    mockMvc
        .perform(get("/api/orders"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
            [
              {
                "id" : "menudo-0001",
                "orderNumber" : 1000,
                "createdAt" : "2026-10-05 14:51:46",
                "customerName" : "Suki",
                "tableNumber" : 10,
                "status" : "PENDING",
                "totalAmount" : 1000.0
              }, {
                "id" : "menudo-0002",
                "orderNumber" : 1001,
                "createdAt" : "2026-10-05 14:51:46",
                "customerName" : "Aaron",
                "tableNumber" : 11,
                "status" : "PREPARING",
                "totalAmount" : 1200.0
              }
            ]
            """));

  }

  @Test
  void shouldGetOrderById() throws Exception {
    Orders order = new Orders("menudo-0002", 1001, "2026-10-05 14:51:46",
        "Aaron", 11, Status.PREPARING, 1200.0f);

    when(ordersService.getOrderById("menudo-0002")).thenReturn(order);

    mockMvc
        .perform(get("/api/orders/{id}", "menudo-0002"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
            {
              "id" : "menudo-0002",
              "orderNumber" : 1001,
              "createdAt" : "2026-10-05 14:51:46",
              "customerName" : "Aaron",
              "tableNumber" : 11,
              "status" : "PREPARING",
              "totalAmount" : 1200.0
            }
            """));
  }

  @Test
  void shouldCreateOrder() throws Exception {
    Orders order = new Orders("menudo-4444", 4444, "2026-10-05 14:51:46", "Alien", 44, Status.SERVED, 4444.4f);

    when(ordersService.createOrder(any(Orders.class))).thenReturn(order);

    mockMvc
        .perform(post("/api/orders")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(order)))
        .andExpect(status().isCreated())
        .andExpect(content().json("""
                {
                  "id" : "menudo-4444",
                  "orderNumber" : 4444,
                  "createdAt" : "2026-10-05 14:51:46",
                  "customerName" : "Alien",
                  "tableNumber" : 44,
                  "status" : "SERVED",
                  "totalAmount" : 4444.4
                }
            """));

  }

  @Test
  void shouldUpdateOrder() throws Exception {
    Orders order = new Orders("menudo-4444", 0004, "2026-10-05 14:51:46", "Barry", 4, Status.CANCELLED, 4900.0f);

    when(ordersService.updateOrder(eq("menudo-4444"), any(Orders.class))).thenReturn(order);

    mockMvc
        .perform(patch("/api/orders/{id}", "menudo-4444")
            .contentType("application/json")
            .content(objectMapper.writeValueAsBytes(order)))
        .andExpect(status().isOk())
        .andExpect(content().json("""
                {
                  "id" : "menudo-4444",
                  "orderNumber" : 0004,
                  "createdAt" : "2026-10-05 14:51:46",
                  "customerName" : "Barry",
                  "tableNumber" : 4,
                  "status" : "CANCELLED",
                  "totalAmount" : 4900.0
                }
            """));
  }

  @Test
  void shouldDeleteOrder() throws Exception {
    mockMvc
        .perform(delete("/api/orders/{id}", "menudo-4444"))
        .andExpect(status().isNoContent());

    verify(ordersService).deleteOrder("menudo-4444");
  }
}
