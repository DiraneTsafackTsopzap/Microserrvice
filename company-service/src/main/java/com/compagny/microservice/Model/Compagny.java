package com.compagny.microservice.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;


@Entity
public class Compagny {

     @Id
     public String id  = UUID.randomUUID().toString();
    public String name;
    public String description;




}