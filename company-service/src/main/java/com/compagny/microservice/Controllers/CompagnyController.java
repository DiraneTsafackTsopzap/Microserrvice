package com.compagny.microservice.Controllers;

import com.compagny.microservice.Model.Compagny;
import com.compagny.microservice.RabbitMQProducer.Job;
import com.compagny.microservice.RabbitMQProducer.Rabbit_Job_Producer;
import com.compagny.microservice.Repositories.ICompagnyRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/compagny")
public class CompagnyController {

   // private final Rabbit_Job_Producer rabbit_Job_Producer;
    private ICompagnyRepositories my_repository;

    private final RabbitTemplate rabbitTemplate;
    private final Queue jobQueue;
    private final ObjectMapper objectMapper;



    public CompagnyController(ICompagnyRepositories repo , RabbitTemplate rabbitTemplate, Queue jobQueue, ObjectMapper objectMapper) {
        this.my_repository = repo;


        this.rabbitTemplate = rabbitTemplate;
        this.jobQueue = jobQueue;
        this.objectMapper = objectMapper;
    }



    @PostMapping
    public ResponseEntity<String> createCompagny(@RequestBody Compagny newCompagny) {
        try {
            my_repository.Create_Compagny(newCompagny);
            return ResponseEntity.ok("Entreprise créée avec succès !");
        } catch (Exception e) {
            // Vous pouvez personnaliser le message d'erreur et le code de statut selon vos besoins
            return ResponseEntity.status(500).body("Erreur lors de la création de l'entreprise : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compagny> get_compagny_id(@PathVariable String id){
        Compagny compagnyexiste = my_repository.get_Compagny_By_Id(id);
        if(compagnyexiste != null)
            return new ResponseEntity<>(compagnyexiste, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Compagny>> findAll(){
        return ResponseEntity.ok(my_repository.Find_All_Compagny());
    }

    @PostMapping("/sendJob")
    public ResponseEntity<String> sendJobToQueue(@RequestBody Job jobMessage) {
        try {
            rabbitTemplate.convertAndSend(jobQueue.getName(), jobMessage);
            return ResponseEntity.ok("Message sent to RabbitMQ successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Echec to send message to RabbitMQ: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete_Compagny(@PathVariable String  id){
        boolean deleted = my_repository.Delete_Compagny_By_Id(id);
        if (deleted)
            return new ResponseEntity<>("Compagny Supprimer avec  Succes",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCompagny(@PathVariable String id,
                                                 @RequestBody Compagny updatedJob){
        boolean updated = my_repository.Update_Compagny(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }








}


