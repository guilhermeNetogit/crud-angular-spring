package com.guilhermeneto.crud_spring.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.guilhermeneto.crud_spring.models.Contatos;

import jakarta.validation.constraints.NotBlank;

public record ParceiroRequestDto(

    @NotBlank(message = "O campo name não pode ficar em branco.")
    String name,
    Integer position,
    String symbol,
    BigDecimal weight,
    List<Contatos> contatos) {}
