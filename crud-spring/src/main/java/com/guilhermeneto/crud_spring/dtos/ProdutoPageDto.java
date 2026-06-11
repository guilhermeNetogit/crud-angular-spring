package com.guilhermeneto.crud_spring.dtos;

import java.util.List;

public record ProdutoPageDto (List<ProdutoResponseDto> produtos, long totalProdutos, int totalPages) {
    
}
