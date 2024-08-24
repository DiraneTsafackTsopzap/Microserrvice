package com.job.microservice.Repositories;

import com.job.microservice.Model.Job;
import com.job.microservice.external.JobWithCompagny;

import java.util.List;

public interface IJobRepositories {

    List<Job> Find_All_Job();
    void Create_Job(Job job);

    Job get_Job_By_Id(String id);

    boolean Delete_Job_By_Id(String id);

    boolean Update_Job(String id, Job updatedJob);
    List<JobWithCompagny> Find_All_Job_s();
}
