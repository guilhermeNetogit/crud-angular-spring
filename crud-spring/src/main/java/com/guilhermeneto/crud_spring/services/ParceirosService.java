package com.guilhermeneto.crud_spring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.guilhermeneto.crud_spring.dtos.ParceiroPageDto;
import com.guilhermeneto.crud_spring.dtos.ParceiroRequestDto;
import com.guilhermeneto.crud_spring.dtos.ParceiroResponseDto;
import com.guilhermeneto.crud_spring.dtos.mapper.ParceiroMapper;
import com.guilhermeneto.crud_spring.exceptions.RecordNotFound;
import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Service
@Validated
public class ParceirosService {

    private final ParceirosRepository parceiroRepository;
    private final ParceiroMapper parceiroMapper;

    public ParceirosService(ParceirosRepository parceiroRepository, ParceiroMapper parceiroMapper) {
        this.parceiroRepository = parceiroRepository;
        this.parceiroMapper = parceiroMapper;
    }

    public ParceiroPageDto getParceiros(@PositiveOrZero int page, @Positive @Max(100) int size, String name) {
        Page<Parceiros> pageParceiro;
        Pageable pageable = PageRequest.of(page, size);

        if (name != null && !name.trim().isEmpty()) {
                pageParceiro = parceiroRepository.findByNameContainingIgnoreCase(name, pageable);
            } else {
                pageParceiro = parceiroRepository.findAll(pageable);
            }

        List<ParceiroResponseDto> parceiros = pageParceiro.getContent().stream()
                .map(parceiroMapper::toDto)
                .collect(Collectors.toList());

        return new ParceiroPageDto(parceiros, pageParceiro.getTotalElements(), pageParceiro.getTotalPages());
    }

    /*
     * public List<ParceiroResponseDto> getParceiros() {
     * List<Parceiros> parceiros = parceiroRepository.findAll();
     * return parceiros.stream()
     * .map(parceiroMapper::toDto)
     * .toList();
     * }
     */

    public ParceiroResponseDto getOne(@Valid Integer id) {
        return parceiroRepository.findById(id).map(parceiroMapper::toDto)
                .orElseThrow(() -> new RecordNotFound(id));
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
                    // Parceiros parceiros = parceiroMapper.toEntity(parceiroDto);
                    recordFound.setName(parceiroDto.name());
                    recordFound.setPosition(parceiroDto.position());
                    recordFound.setSymbol(parceiroDto.symbol());
                    recordFound.setWeight(parceiroDto.weight());
                    // recordFound.setContatos(parceiros.getContatos());

                    Parceiros tempEntity = parceiroMapper.toEntity(parceiroDto);
                    recordFound.getContatos().clear(); // Limpa a lista existente monitorada pelo JPA
                    if (tempEntity.getContatos() != null) {
                        tempEntity.getContatos().forEach(contato -> {
                            contato.setParceiros(recordFound);
                            recordFound.getContatos().add(contato);
                        });
                    }

                    return parceiroRepository.save(recordFound);
                }).map(parceiroMapper::toDto).orElseThrow(() -> new RecordNotFound(id));
    }

    public void delete(@Valid Integer id) {
        parceiroRepository.delete(parceiroRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound(id)));
    }

}
