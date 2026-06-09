package com.guilhermeneto.crud_spring.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.models.Produtos;
import com.guilhermeneto.crud_spring.repository.ProdutosRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de produtos do sistema")
public class ProdutosController {
    
    private final ProdutosRepository produtoRepository;
    
        // Injeção de dependência via construtor
        public ProdutosController(ProdutosRepository produtoRepository) {
            this.produtoRepository = produtoRepository;
        }
    @GetMapping
        public Page<Produtos> list(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "") String nome) {
            
            // Faz a busca paginada filtrando por nome (ignora maiúsculas/minúsculas)
            return produtoRepository.findByDescrprodContainingIgnoreCase(nome, PageRequest.of(page, size));
        }
}
