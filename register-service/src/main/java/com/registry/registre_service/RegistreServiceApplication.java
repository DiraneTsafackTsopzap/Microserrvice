package com.registry.registre_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //------------------------------------ 1- placer ceci
public class RegistreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistreServiceApplication.class, args);
	}

}


// 1- Placer EnableEurekaServer ds Mon Main Application de Mon RegistreServiceApplication
// 2- Definir ensuite le Application.properties