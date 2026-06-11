package com.guilhermeneto.crud_spring.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDto(

    @NotBlank(message = "O campo name não pode ficar em branco.")
    @NotBlank @NotNull String descrprod,
    String compldesc,
    String symbol,
    String codvol,
    String eangtin,
    String referencia,
    LocalDateTime dtcreated,
    LocalDateTime dtalter
) {}
