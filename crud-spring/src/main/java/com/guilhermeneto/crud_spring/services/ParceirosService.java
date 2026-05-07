package com.guilhermeneto.crud_spring.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
        .map(parceiroMapper::tDto)
        .toList();
    }

    public ParceiroResponseDto getOne(@Valid Integer id) {
        return parceiroRepository.findById(id).map(parceiroMapper::tDto)
        .orElseThrow(() -> new RecordNotFound(id));/*
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());*/
    }

    public ParceiroResponseDto save(@Valid @NotNull ParceiroResponseDto parceiro) {
        return parceiroMapper.tDto(parceiroRepository.save(parceiroMapper.toEntity(parceiro)));
        /* return ResponseEntity.status(201).body(parceiroRepository.save(parceiro)); */
    }

    public ParceiroResponseDto update(Integer id, @Valid @NotNull ParceiroResponseDto parceiro) {
        return parceiroRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(parceiro.name());
                    recordFound.setPosition(parceiro.position());
                    recordFound.setSymbol(parceiro.symbol());
                    recordFound.setWeight(parceiro.weight());

                    return parceiroRepository.save(recordFound);
                }).map(parceiroMapper::tDto).orElseThrow(() -> new RecordNotFound(id));
    }

    public void delete(@Valid Integer id) {
        parceiroRepository.delete(parceiroRepository.findById(id)
            .orElseThrow(() -> new RecordNotFound(id)));
    }

}
