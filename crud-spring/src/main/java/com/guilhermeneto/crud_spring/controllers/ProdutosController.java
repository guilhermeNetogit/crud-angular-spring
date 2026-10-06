package com.guilhermeneto.crud_spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.dtos.ProdutoPageDto;
import com.guilhermeneto.crud_spring.dtos.ProdutoRequestDto;
import com.guilhermeneto.crud_spring.dtos.ProdutoResponseDto;
import com.guilhermeneto.crud_spring.services.ProdutosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
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
    @Operation(summary = "Lista os produtos paginados")
    public ProdutoPageDto getProdutos(
            @RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(value = "size", defaultValue = "10") @Positive @Max(100) int size,
            @RequestParam(value = "name", defaultValue = "") String name) {

        return produtoService.getProdutos(page, size, name);
    }

    @GetMapping("/{codprod}")
    public ProdutoResponseDto getOne(@PathVariable @NotNull @Positive Integer codprod) {
        return produtoService.getOne(codprod);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cria um novo produto")
    public ProdutoResponseDto create(@RequestBody @Valid ProdutoRequestDto produtoDto) {
        return produtoService.save(produtoDto);
    }

    @PutMapping("/{codprod}")
    public ProdutoResponseDto update(
            @PathVariable @NotNull @Positive Integer codprod,
            @RequestBody @Valid ProdutoRequestDto produtoDto) {
        return produtoService.update(codprod, produtoDto);
    }

    // Soft Delete: altera o status sem remover do banco
    @DeleteMapping("/{codprod}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclusão lógica do produto (Soft Delete)")
    public void softDelete(@PathVariable @NotNull @Positive Integer codprod) {
        produtoService.softDelete(codprod);
    }

    // Hard Delete: remove fisicamente do banco de dados
    @DeleteMapping("/{codprod}/hard")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclusão física do produto (Hard Delete)")
    public void delete(@PathVariable @NotNull @Positive Integer codprod) {
        produtoService.delete(codprod);
    }
}
