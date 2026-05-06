package com.guilhermeneto.crud_spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class ParceirosService {
    
    private final ParceirosRepository parceiroRepository;
    
    public ParceirosService(ParceirosRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    public List<Parceiros> getParceiros() {
        return parceiroRepository.findAll();
    }

    public Optional<Parceiros> getOne(@PathVariable @Valid Integer id) {
        return parceiroRepository.findById(id)/*
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build())*/;
    }

    public Parceiros save(@Valid Parceiros parceiro) {
        return parceiroRepository.save(parceiro);
        /* return ResponseEntity.status(201).body(parceiroRepository.save(parceiro)); */
    }

    public Optional<Parceiros> update(Integer id, @Valid Parceiros parceiro) {

        parceiro.setId(id);
        return parceiroRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(parceiro.getName());
                    recordFound.setPosition(parceiro.getPosition());
                    recordFound.setSymbol(parceiro.getSymbol());
                    recordFound.setWeight(parceiro.getWeight());

                    return parceiroRepository.save(recordFound);

                });
    }

    public boolean delete(@PathVariable @Valid Integer id) {
        return parceiroRepository.findById(id)
                .map(recordFound -> {
                    parceiroRepository.deleteById(id);
                    ;
                    return true;
                })
                .orElse(false);
    }

}
