package com.guilhermeneto.crud_spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/parceiros")
@AllArgsConstructor
public class ParceirosController {

    private final ParceirosRepository repository;

    @GetMapping()
    public List<Parceiros> getParceiros() {
        return repository.findAll();
    }
}
