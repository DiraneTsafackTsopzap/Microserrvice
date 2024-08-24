package com.compagny.microservice.RabbitMQProducer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service

public class Rabbit_Job_Producer {

    private final RabbitTemplate rabbitTemplate;

    public Rabbit_Job_Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJobToRabbitMq(Job job) {
        rabbitTemplate.convertAndSend("JobQueue", job);
    }
}

