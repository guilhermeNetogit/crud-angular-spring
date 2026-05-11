package com.guilhermeneto.crud_spring.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.guilhermeneto.crud_spring.dtos.ParceiroRequestDto;
import com.guilhermeneto.crud_spring.dtos.ParceiroResponseDto;
import com.guilhermeneto.crud_spring.dtos.mapper.ParceiroMapper;
import com.guilhermeneto.crud_spring.exceptions.RecordNotFound;
import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class ParceirosService {
    
    private final ParceirosRepository parceiroRepository;
    private final ParceiroMapper parceiroMapper;
    
    public ParceirosService(ParceirosRepository parceiroRepository, ParceiroMapper parceiroMapper) {
        this.parceiroRepository = parceiroRepository;
        this.parceiroMapper = parceiroMapper;
    }

    public List<ParceiroResponseDto> getParceiros() {
        List<Parceiros> parceiros =  parceiroRepository.findAll();
        return parceiros.stream()
        .map(parceiroMapper::toDto)
        .toList();
    }

    public ParceiroResponseDto getOne(@Valid Integer id) {
        return parceiroRepository.findById(id).map(parceiroMapper::toDto)
        .orElseThrow(() -> new RecordNotFound(id));/*
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());*/
    }

    public ParceiroResponseDto save(@Valid @NotNull ParceiroRequestDto parceiroDto) {

        Parceiros entity = parceiroMapper.toEntity(parceiroDto);

        if (entity.getContatos() != null) {
                entity.getContatos().forEach(contato -> contato.setParceiros(entity));
            }
            
        return parceiroMapper.toDto(parceiroRepository.save(entity));
        /* return ResponseEntity.status(201).body(parceiroRepository.save(parceiro)); */
    }

    public ParceiroResponseDto update(Integer id, @Valid @NotNull ParceiroRequestDto parceiroDto) {
        return parceiroRepository.findById(id)
                .map(recordFound -> {
                    Parceiros parceiros = parceiroMapper.toEntity(parceiroDto);
                    recordFound.setName(parceiroDto.name());
                    recordFound.setPosition(parceiroDto.position());
                    recordFound.setSymbol(parceiroDto.symbol());
                    recordFound.setWeight(parceiroDto.weight());
                    recordFound.setContatos(parceiros.getContatos());

                    return parceiroRepository.save(recordFound);
                }).map(parceiroMapper::toDto).orElseThrow(() -> new RecordNotFound(id));
    }

    public void delete(@Valid Integer id) {
        parceiroRepository.delete(parceiroRepository.findById(id)
            .orElseThrow(() -> new RecordNotFound(id)));
    }

}
