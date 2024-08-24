package com.compagny.microservice.Repositories;



import com.compagny.microservice.JPA_Compagny_Repository.ICompagny_JPA_Repository;
import com.compagny.microservice.Model.Compagny;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompagnyRepositories implements  ICompagnyRepositories {


    ICompagny_JPA_Repository repositories_Compagny;



    public  CompagnyRepositories ( ICompagny_JPA_Repository jobRepositoryJpa ) {

        this.repositories_Compagny = jobRepositoryJpa;


    }

    @Override
    public List<Compagny> Find_All_Compagny() {
        return repositories_Compagny.findAll();
    }

    @Override
    public void Create_Compagny(Compagny new_compagny) {


        // Sauvegarde de l'entreprise dans le repository
        repositories_Compagny.save(new_compagny);
    }

    @Override
    public Compagny get_Compagny_By_Id(String id) {
        return repositories_Compagny.findById(id).orElse(null);
    }

    @Override
    public boolean Delete_Compagny_By_Id(String id) {
        try {
            repositories_Compagny.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean Update_Compagny(String id, Compagny compagnytoupdate) {
        Optional<Compagny> compagnyOptional = repositories_Compagny.findById(id);
        if (compagnyOptional.isPresent()) {
            Compagny job = compagnyOptional.get();
            job.name = compagnytoupdate.name;
            job.description = compagnytoupdate.description;
            repositories_Compagny.save(job); // Sauvegarde de l'entité mise à jour
            return true;
        }
        return false;
    }
}
