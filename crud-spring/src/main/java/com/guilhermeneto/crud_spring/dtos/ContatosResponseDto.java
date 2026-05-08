package com.guilhermeneto.crud_spring.dtos;

public record ContatosResponseDto(
    Integer codcontato,
    String nomecontato,
    String email,
    String telefone,
    String siteurl
) {}
