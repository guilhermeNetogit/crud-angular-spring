package com.guilhermeneto.crud_spring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/parceiros")
@AllArgsConstructor
public class ParceirosController {

    private final ParceirosRepository parceiroRepository;

    @GetMapping()
    public List<Parceiros> getParceiros() {
        return parceiroRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Parceiros save(@RequestBody Parceiros parceiro) {
        return parceiroRepository.save(parceiro);
        /*return ResponseEntity.status(201).body(parceiroRepository.save(parceiro));*/
    }

}
