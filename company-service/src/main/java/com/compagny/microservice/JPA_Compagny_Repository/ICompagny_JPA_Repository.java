package com.compagny.microservice.JPA_Compagny_Repository;

import com.compagny.microservice.Model.Compagny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompagny_JPA_Repository extends JpaRepository<Compagny, String> {


}


