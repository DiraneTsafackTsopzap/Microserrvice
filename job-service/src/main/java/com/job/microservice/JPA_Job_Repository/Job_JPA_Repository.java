package com.job.microservice.JPA_Job_Repository;


import com.job.microservice.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Job_JPA_Repository extends JpaRepository<Job, String> {

}