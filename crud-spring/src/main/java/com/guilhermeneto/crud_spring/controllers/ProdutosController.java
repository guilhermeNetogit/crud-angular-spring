package com.guilhermeneto.crud_spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.dtos.ProdutoPageDto;
import com.guilhermeneto.crud_spring.services.ProdutosService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de produtos do sistema")
public class ProdutosController {
    
    private final ProdutosService produtoService;
    
        // Injeção de dependência via construtor
        public ProdutosController(ProdutosService produtoService) {
            this.produtoService = (ProdutosService) produtoService;
        }
    @GetMapping
        public ProdutoPageDto getProdutos(
                @RequestParam(value = "page", defaultValue = "0") 
                @PositiveOrZero int page,
                @RequestParam(value = "size", defaultValue = "10") 
                @Positive @Max(100) int size,
                @RequestParam(value = "name", defaultValue = "") 
                String name) {

            {
        return produtoService.getProdutos(page, size, name);
    }
        }
}
