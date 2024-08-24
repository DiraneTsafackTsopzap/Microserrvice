package com.job.microservice.RabbitConsumer;


import com.job.microservice.JPA_Job_Repository.Job_JPA_Repository;
import com.job.microservice.Model.Job;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JobConsumer {

    @Autowired
    private Job_JPA_Repository jobRepository;

    @RabbitListener(queues = "jobQueue")
    public void receiveMessage(Job job) {

        jobRepository.save(job);
    }
}
