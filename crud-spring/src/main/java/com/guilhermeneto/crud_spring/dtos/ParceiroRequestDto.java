package com.guilhermeneto.crud_spring.dtos;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParceiroRequestDto(

    @NotBlank(message = "O campo name não pode ficar em branco.")
    @NotBlank @NotNull String name,
    Integer position,
    String symbol,
    BigDecimal weight,
    List<ContatosResponseDto> contatos) {}
