package com.guilhermeneto.crud_spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guilhermeneto.crud_spring.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
Page<Produtos> findByDescrprodContainingIgnoreCase(String descrprod, Pageable pageable);
}
