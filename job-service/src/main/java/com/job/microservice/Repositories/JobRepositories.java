package com.job.microservice.Repositories;

import com.job.microservice.JPA_Job_Repository.Job_JPA_Repository;
import com.job.microservice.Model.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.job.microservice.external.Compagny;
import com.job.microservice.external.JobWithCompagny;

@Service
public class JobRepositories implements IJobRepositories {
    Job_JPA_Repository jobRepositoryJpa;


    @Autowired


public  JobRepositories (Job_JPA_Repository jobRepositoryJpa ) {
    this.jobRepositoryJpa = jobRepositoryJpa;

}




@Override
public List<JobWithCompagny> Find_All_Job_s() {

    // Récupère la liste de tous les jobs
    List<Job> jobs_listes = jobRepositoryJpa.findAll();
    List<JobWithCompagny> job_with_compagnies = new ArrayList<>();



    // Pour chaque job dans la liste
    for (Job job : jobs_listes) {
        JobWithCompagny job_with_Compagny = new JobWithCompagny(); 
        job_with_Compagny.job = job;



        // Création d'un RestTemplate pour effectuer un appel REST
        RestTemplate rest_Template = new RestTemplate();

        // Récupération de l'objet Compagny associé via l'ID de la compagnie
       Compagny compagny = rest_Template.getForObject("http://localhost:8080/compagny/" + job.compagnyid, Compagny.class);


        job_with_Compagny.compagny= compagny;

        // Ajout du job avec la compagnie associée dans la liste
        job_with_compagnies.add(job_with_Compagny);
    }

    return job_with_compagnies;
}


@Override
public List<Job> Find_All_Job() {

    return jobRepositoryJpa.findAll();
}

@Override
public void Create_Job(Job job) {

    jobRepositoryJpa.save(job);
}

@Override
public Job get_Job_By_Id(String id) {
    return jobRepositoryJpa.findById(id).orElse(null);
}

@Override
public boolean Delete_Job_By_Id(String id) {
    try {
        jobRepositoryJpa.deleteById(id);
        return true;
    } catch (Exception e) {
        return false;
    }
}

@Override
public boolean Update_Job(String id, Job updatedJob) {
    Optional<Job> jobOptional = jobRepositoryJpa.findById(id);
    if (jobOptional.isPresent()) {
        Job job = jobOptional.get();
        job.titre = updatedJob.titre;
        job.description = updatedJob.description;
        job.maxSalary = updatedJob.maxSalary;
        job.minSalary = updatedJob.minSalary;
        job.location = updatedJob.location;
        jobRepositoryJpa.save(job); // Sauvegarde de l'entité mise à jour
        return true;
    }
    return false;
}
}

