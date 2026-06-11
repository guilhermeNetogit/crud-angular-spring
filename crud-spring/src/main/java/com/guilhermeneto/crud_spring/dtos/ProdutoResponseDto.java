package com.guilhermeneto.crud_spring.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProdutoResponseDto(
    
    
    Integer codprod,

    @Schema(example = "Descrição do produto")
    String descrprod,

    @Schema(example = "Complemento da descrição")
    String compldesc,
    
    @Schema(example = "UN")
    String codvol,

    @Schema(example = "74589652798")
    String eangtin,

    String referencia,
    
    @Schema(hidden = true)
    LocalDateTime dtcreated,
    
    @Schema(hidden = true)
    LocalDateTime dtalter
) {}
