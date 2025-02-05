package com.mjp.factory.adapters.inbound.brokers.sqs;

import com.mjp.factory.adapters.inbound.brokers.PenBroker;
import com.mjp.factory.application.usecases.PenUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@DependsOn("ssmConfig")
public class SqsConsumer implements PenBroker {

    private final PenUseCase service;

    public SqsConsumer(PenUseCase service) {
        this.service = service;
    }



    @Override
    @SqsListener("${listener}")
    public void readMessage(String message) {
        service.createObject(message);
    }


}
