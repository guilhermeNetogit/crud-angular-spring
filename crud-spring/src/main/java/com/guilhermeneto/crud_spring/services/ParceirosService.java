package com.guilhermeneto.crud_spring.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.guilhermeneto.crud_spring.exceptions.RecordNotFound;
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

    public Parceiros getOne(@PathVariable @Valid Integer id) {
        return parceiroRepository.findById(id).orElseThrow(() -> new RecordNotFound(id));/*
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());*/
    }

    public Parceiros save(@Valid Parceiros parceiro) {
        return parceiroRepository.save(parceiro);
        /* return ResponseEntity.status(201).body(parceiroRepository.save(parceiro)); */
    }

    public Parceiros update(Integer id, @Valid Parceiros parceiro) {

        parceiro.setId(id);
        return parceiroRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(parceiro.getName());
                    recordFound.setPosition(parceiro.getPosition());
                    recordFound.setSymbol(parceiro.getSymbol());
                    recordFound.setWeight(parceiro.getWeight());

                    return parceiroRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFound(id));
    }

    public void delete(@PathVariable @Valid Integer id) {

        parceiroRepository.delete(parceiroRepository.findById(id)
            .orElseThrow(() -> new RecordNotFound(id)));
    }

}
