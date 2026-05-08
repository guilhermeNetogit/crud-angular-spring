package com.guilhermeneto.crud_spring.dtos;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record ParceiroResponseDto(
    
    @Schema(hidden = true)
    Integer id,

    @Schema(example = "Element")
    String name,

    @Schema(example = "130")
    Integer position,
    
    @Schema(example = "Xy")
    String symbol,
    @Schema(example = "100.99")
    BigDecimal weight,
    List<ContatosResponseDto> contatos
) {}
