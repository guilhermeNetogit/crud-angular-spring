package com.guilhermeneto.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

@SpringBootApplication
public class CrudSpringApplication {

	CrudSpringApplication(ParceirosRepository parceirosRepository) {
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner initDatabase(ParceirosRepository repository) {
		return args -> {
		
		System.out.println("\n=================================================");
		System.out.println("Banco de dados carregado com sucesso!");
		System.out.println("=================================================\n");
		};
	}
}