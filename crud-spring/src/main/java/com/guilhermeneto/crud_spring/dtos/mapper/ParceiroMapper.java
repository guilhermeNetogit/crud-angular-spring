package com.guilhermeneto.crud_spring.dtos.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guilhermeneto.crud_spring.dtos.ContatosResponseDto;
import com.guilhermeneto.crud_spring.dtos.ParceiroRequestDto;
import com.guilhermeneto.crud_spring.dtos.ParceiroResponseDto;
import com.guilhermeneto.crud_spring.models.Parceiros;

@Component
public class ParceiroMapper {
    
    public ParceiroResponseDto tDto(Parceiros parceiro) {
        if (parceiro == null) {
            return null;
        }

        List<ContatosResponseDto> contatosDto = parceiro.getContatos()
                    .stream()
                    .map(c -> new ContatosResponseDto(c.getCodcontato(), c.getEmail(), c.getNomecontato(),c.getTelefone(),c.getSiteurl()))
                    .toList();

        return new ParceiroResponseDto(
             parceiro.getId(),
             parceiro.getName(),
             parceiro.getPosition(),
             parceiro.getSymbol(),
             parceiro.getWeight(),
             contatosDto
        );
    }

    public Parceiros toEntity(ParceiroRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Parceiros parceiros = new Parceiros();
        
        parceiros.setName(dto.name());
        parceiros.setPosition(dto.position());
        parceiros.setSymbol(dto.symbol());
        parceiros.setWeight(dto.weight());  
        
        return parceiros;
    }
}
