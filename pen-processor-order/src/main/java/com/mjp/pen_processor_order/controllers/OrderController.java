package com.mjp.pen_processor_order.controllers;

import com.mjp.pen_processor_order.dto.OrderProcessDTO;
import com.mjp.pen_processor_order.dto.PenDTO;
import com.mjp.pen_processor_order.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/pen")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<OrderProcessDTO> createPen(@RequestBody PenDTO pen, @RequestParam("quantity") Integer quantity){
        OrderProcessDTO pen1 = service.createPen(pen, quantity);
        return ResponseEntity.ok().body(pen1);
    }
}
