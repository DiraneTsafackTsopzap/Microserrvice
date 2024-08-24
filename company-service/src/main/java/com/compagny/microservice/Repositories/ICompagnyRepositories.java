package com.compagny.microservice.Repositories;

import com.compagny.microservice.Model.Compagny;

import java.util.List;

public interface ICompagnyRepositories {

    List<Compagny> Find_All_Compagny();
    void Create_Compagny(Compagny new_compagny);

    Compagny get_Compagny_By_Id(String id);

    boolean Delete_Compagny_By_Id(String id);

    boolean Update_Compagny(String id, Compagny compagny);
}
