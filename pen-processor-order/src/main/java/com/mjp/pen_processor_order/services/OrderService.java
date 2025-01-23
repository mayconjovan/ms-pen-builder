package com.mjp.pen_processor_order.services;

import com.mjp.pen_processor_order.dto.OrderProcessDTO;
import com.mjp.pen_processor_order.dto.PenDTO;
import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.entities.Pen;
import com.mjp.pen_processor_order.producer.SnsProducer;
import com.mjp.pen_processor_order.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final SnsProducer snsProducer;


    public OrderService(OrderRepository repository, SnsProducer snsProducer) {
        this.repository = repository;
        this.snsProducer = snsProducer;
    }


    public OrderProcessDTO createPen(PenDTO pen) {
        var penx = new Pen(null,Instant.now(),"started");
        penx.setPenDTO(pen);
        var process = new OrderProcess(null, Instant.now(), penx, pen.quantity());
        try{
            snsProducer.publishToTopic("topic", pen.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }


        var processSaved = repository.save(process);

        return new OrderProcessDTO(processSaved.getId().toString(), "xpt", 10.0, 10, pen);
    }




}
