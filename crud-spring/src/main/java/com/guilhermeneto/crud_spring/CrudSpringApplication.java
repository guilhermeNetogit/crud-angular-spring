package com.guilhermeneto.crud_spring;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.guilhermeneto.crud_spring.models.Contatos;
import com.guilhermeneto.crud_spring.models.Parceiros;
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

		List<Parceiros> lista = ParceirosDataSource.getParceiros();

		Contatos c = new Contatos();
			c.setNomecontato("Vendedor 1");
			c.setTelefone("3433363232");
			c.setEmail("vendedor1@loja1.com.br");

		if (!lista.isEmpty()) {
			Parceiros p = lista.get(0);
			c.setParceiros(p);
			p.getContatos().add(c);
		}
		
		parceirosRepository.saveAll(lista);
		
		System.out.println("Banco de dados carregado com sucesso!");
		};
	}
}