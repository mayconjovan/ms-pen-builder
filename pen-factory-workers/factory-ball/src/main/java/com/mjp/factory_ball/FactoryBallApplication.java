package com.mjp.factory_ball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FactoryBallApplication implements Runnable{

	public static void main(String[] args) {
		SpringApplication.run(FactoryBallApplication.class, args);
	}

	@Override
	public void run() {
		System.out.println("${sqs.queue}");
	}
}
