package com.guilhermeneto.crud_spring.dtos.mapper;

import org.springframework.stereotype.Component;

import com.guilhermeneto.crud_spring.dtos.ParceiroResponseDto;
import com.guilhermeneto.crud_spring.models.Parceiros;

@Component
public class ParceiroMapper {
    
    public ParceiroResponseDto tDto(Parceiros parceiro) {
        if (parceiro == null) {
            return null;
        }

        return new ParceiroResponseDto(
             parceiro.getId(),
             parceiro.getName(),
             parceiro.getPosition(),
             parceiro.getSymbol(),
             parceiro.getWeight()
        );
    }

    public Parceiros toEntity(ParceiroResponseDto parceiroResponseDto) {

        if (parceiroResponseDto == null) {
            return null;
        }

        Parceiros parceiros = new Parceiros();
        if (parceiroResponseDto.id() != null) {
            parceiros.setId(parceiroResponseDto.id());
        }
        parceiros.setName(parceiroResponseDto.name());
        parceiros.setPosition(parceiroResponseDto.position());
        parceiros.setSymbol(parceiroResponseDto.symbol());
        parceiros.setWeight(parceiroResponseDto.weight());        

        return parceiros;
    }
}
