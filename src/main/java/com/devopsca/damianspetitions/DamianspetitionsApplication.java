package com.devopsca.damianspetitions;

import com.devopsca.damianspetitions.service.PetitionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DamianspetitionsApplication {

	@Autowired
	private PetitionService petitionService;

	public static void main(String[] args) {
		SpringApplication.run(DamianspetitionsApplication.class, args);
	}


	@PostConstruct
	public void init() {
		petitionService.initializeDefaultPetitions();
	}
}
