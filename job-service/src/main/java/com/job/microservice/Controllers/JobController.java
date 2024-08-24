package com.job.microservice.Controllers;


import com.job.microservice.Model.Job;
import com.job.microservice.Repositories.JobRepositories;
import com.job.microservice.external.JobWithCompagny;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepositories repositories;


    public JobController(JobRepositories jobRepositories) {

        this.repositories = jobRepositories;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(repositories.Find_All_Job());
    }



    /// Si u veux use cette Propietes , assure toi dabord que tous les compapgnyid existent si non tu auras les erreurs
    // a defaut sußprime tous les jobs presents ici et lors de lajout dun job , assure toi de lid de compagny


    //    @GetMapping
//    public ResponseEntity<List<JobWithCompagny>> findAll(){
//        return ResponseEntity.ok(repositories.Find_All_Job_s());
//    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        repositories.Create_Job(job);
        return new ResponseEntity<>("Job Creer Avec Succes", HttpStatus.CREATED);
    }







    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id){
        Job job = repositories.get_Job_By_Id(id);
        if(job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable String  id){
        boolean deleted = repositories.Delete_Job_By_Id(id);
        if (deleted)
            return new ResponseEntity<>("Job Supprimer avec  Succes",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable String id,
                                            @RequestBody Job updatedJob){
        boolean updated = repositories.Update_Job(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

