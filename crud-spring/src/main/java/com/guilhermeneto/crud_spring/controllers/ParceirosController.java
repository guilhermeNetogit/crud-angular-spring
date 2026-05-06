package com.guilhermeneto.crud_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.services.ParceirosService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/parceiros")
public class ParceirosController {

    @Autowired
    private ParceirosService parceiroService;

    public ParceirosController(ParceirosService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @GetMapping()
    public @ResponseBody List<Parceiros> getParceiros() {
        return parceiroService.getParceiros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parceiros> getOne(@PathVariable @Valid Integer id) {
        return parceiroService.getOne(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid Integer id) {
        if (parceiroService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Parceiros save(@RequestBody @Valid Parceiros parceiro) {
        return parceiroService.save(parceiro);
        /* return ResponseEntity.status(201).body(parceiroRepository.save(parceiro)); */
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiros> update(@PathVariable Integer id, @RequestBody @Valid Parceiros parceiro) {

        parceiro.setId(id);
        return parceiroService.update(id, parceiro)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }
}
