package com.guilhermeneto.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.guilhermeneto.crud_spring.repository.ParceirosDataSource;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

@SpringBootApplication
public class CrudSpringApplication {

	private final ParceirosRepository parceirosRepository;

	CrudSpringApplication(ParceirosRepository parceirosRepository) {
		this.parceirosRepository = parceirosRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ParceirosRepository repository) {

		return args -> {

			repository.deleteAll();

			parceirosRepository.saveAll(ParceirosDataSource.getParceiros());

			System.out.println("Banco de dados carregado com sucesso!");
		};
	}
}
