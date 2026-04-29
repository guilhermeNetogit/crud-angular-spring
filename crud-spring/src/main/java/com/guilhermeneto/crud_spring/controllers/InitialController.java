package com.guilhermeneto.crud_spring.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/inicio")
public class InitialController {

    @GetMapping
    public String inicio() {
        return "Willkommen zu meiner Webanwendung – Spring";
    }

}
