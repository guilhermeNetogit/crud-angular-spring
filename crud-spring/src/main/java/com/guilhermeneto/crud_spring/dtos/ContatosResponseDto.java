package com.guilhermeneto.crud_spring.dtos;

public record ContatosResponseDto(
    Integer codcontato,
    String nomecontato,
    String telefone,
    String email,
    String siteurl
) {}
