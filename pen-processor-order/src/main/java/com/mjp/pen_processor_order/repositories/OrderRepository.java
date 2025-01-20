package com.mjp.pen_processor_order.repositories;

import com.mjp.pen_processor_order.entities.OrderProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderProcess, UUID> {

}
