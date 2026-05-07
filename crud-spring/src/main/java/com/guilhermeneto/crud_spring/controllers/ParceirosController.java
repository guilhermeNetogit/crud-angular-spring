package com.guilhermeneto.crud_spring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.dtos.ParceiroResponseDto;
import com.guilhermeneto.crud_spring.services.ParceirosService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/parceiros")
@Tag(name = "Parceiros", description = "Gerenciamento de parceiros do sistema")
public class ParceirosController {

    private ParceirosService parceiroService;

    public ParceirosController(ParceirosService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @GetMapping()
    public List<ParceiroResponseDto> getParceiros() {
        return parceiroService.getParceiros();
    }

    @GetMapping("/{id}")
    public ParceiroResponseDto getOne(@PathVariable @Valid Integer id) {
        return parceiroService.getOne(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ParceiroResponseDto save(@RequestBody @Valid ParceiroResponseDto parceiro) {
        return parceiroService.save(parceiro);
    }

    @PutMapping("/{id}")
    public ParceiroResponseDto update(@PathVariable Integer id, @RequestBody @Valid ParceiroResponseDto parceiro) {
        return parceiroService.update(id, parceiro);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Integer id) {
        parceiroService.delete(id);
    }
}
