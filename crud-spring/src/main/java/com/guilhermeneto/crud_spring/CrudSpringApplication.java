package com.guilhermeneto.crud_spring;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;
import com.guilhermeneto.crud_spring.repository.datasource.ContatosDatasource;
import com.guilhermeneto.crud_spring.repository.datasource.ParceirosDataSource;

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

		List<Parceiros> lista = ParceirosDataSource.getParceiros();

		ContatosDatasource.getContatos(lista);
		
		parceirosRepository.saveAll(lista);
		
		System.out.println("Banco de dados carregado com sucesso!");
		};
	}
}