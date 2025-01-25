package com.mjp.pen_processor_order.job;

import com.mjp.pen_processor_order.services.OrderService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class OrderProductionJob {

    private final OrderService service;

    public OrderProductionJob(OrderService service) {
        this.service = service;
    }


    @Scheduled(cron = "0 0/5 * * * *")
    public void getAllOrderPaid() {
       // service.getAllOrdersPaid();
    }

}
