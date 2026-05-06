package com.guilhermeneto.crud_spring.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/parceiros")
@AllArgsConstructor
public class ParceirosController {

    private final ParceirosRepository parceiroRepository;

    @GetMapping()
    public List<Parceiros> getParceiros() {
        return parceiroRepository.findAll();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parceiros> getOne(@PathVariable @Valid Integer id) {
        return parceiroRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable @Valid Integer id) {
           return parceiroRepository.findById(id)
           .map(recordFound -> {
            parceiroRepository.deleteById(id);;
            return 
            ResponseEntity.noContent().<Void>build();

        })
        .orElse(ResponseEntity.notFound().build());
        }
 
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Parceiros save(@RequestBody @Valid Parceiros parceiro) {
        return parceiroRepository.save(parceiro);
        /*return ResponseEntity.status(201).body(parceiroRepository.save(parceiro));*/
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiros> update(@PathVariable Integer id, @RequestBody @Valid Parceiros parceiro) {

        parceiro.setId(id);
        return parceiroRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(parceiro.getName());
            recordFound.setPosition(parceiro.getPosition());
            recordFound.setSymbol(parceiro.getSymbol());
            recordFound.setWeight(parceiro.getWeight());

            Parceiros updated = parceiroRepository.save(recordFound);
            return 
            ResponseEntity.ok().body(updated);

        })
        .orElse(ResponseEntity.notFound().build());
    }
}
