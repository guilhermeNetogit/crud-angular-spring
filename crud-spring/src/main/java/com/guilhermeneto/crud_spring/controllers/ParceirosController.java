package com.guilhermeneto.crud_spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.dtos.ParceiroPageDto;
import com.guilhermeneto.crud_spring.dtos.ParceiroRequestDto;
import com.guilhermeneto.crud_spring.dtos.ParceiroResponseDto;
import com.guilhermeneto.crud_spring.services.ParceirosService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/parceiros")
@Tag(name = "Parceiros", description = "Gerenciamento de parceiros do sistema")
public class ParceirosController {

    private final ParceirosService parceiroService;

    public ParceirosController(ParceirosService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @GetMapping()
    public ParceiroPageDto getParceiros(
        @RequestParam(defaultValue = "0") @PositiveOrZero int page, 
        @RequestParam(defaultValue = "10") @Positive @Max(20) int pageSize
    ) {
        return parceiroService.getParceiros(page, pageSize);
    }

    @GetMapping("/{id}")
    public ParceiroResponseDto getOne(@PathVariable Integer id) {
        return parceiroService.getOne(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ParceiroResponseDto save(@RequestBody @Valid ParceiroRequestDto parceiro) {
        return parceiroService.save(parceiro);
    }

    @PutMapping("/{id}")
    public ParceiroResponseDto update(@PathVariable Integer id, @RequestBody ParceiroRequestDto parceiro) {
        return parceiroService.update(id, parceiro);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Integer id) {
        parceiroService.delete(id);
    }
}
