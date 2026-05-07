package com.guilhermeneto.crud_spring.dtos;

import jakarta.validation.constraints.NotBlank;

public record ParceiroRequestDto(

    @NotBlank(message = "O campo name não pode ficar em branco.")
    String name,
    Integer position,
    String symbol,
    Integer weight) {}
