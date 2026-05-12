package com.guilhermeneto.crud_spring.dtos;

import java.util.List;

public record ParceiroPageDto (List<ParceiroResponseDto> parceiros, long totalElements, int totalPages) {
    
}
