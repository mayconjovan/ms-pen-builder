package com.mjp.pen_processor_order.controllers;

import com.mjp.pen_processor_order.dto.OrderProcessDTO;
import com.mjp.pen_processor_order.dto.ProductionOrderRequest;
import com.mjp.pen_processor_order.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<OrderProcessDTO>> listAllOrdersPaged() {
        return ResponseEntity.ok().body(service.listAllOrdersPaged());
    }

    @PostMapping
    public ResponseEntity<OrderProcessDTO> createProductionOrder(@RequestBody ProductionOrderRequest orderRequest) {
        OrderProcessDTO orderProcess = service.createProductionOrder(orderRequest);
        return ResponseEntity.ok().body(orderProcess);
    }

    @PostMapping("/start-production")
    public ResponseEntity<List<Integer>> startProductionProcess() {
        List<Integer> orderNumber = service.startProductionProcess();
        return ResponseEntity.ok(orderNumber);
    }
}
