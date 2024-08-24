package com.compagny.microservice.Compagny_Client;


import com.compagny.microservice.Model.Compagny;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="compagny-service")
public interface CompagnyClient {

    @GetMapping("/compagny/{id}")
    Compagny get_compagny_by_id(@PathVariable("id") String id);
}
