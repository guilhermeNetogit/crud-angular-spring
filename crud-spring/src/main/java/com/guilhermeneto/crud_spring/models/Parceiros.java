package com.guilhermeneto.crud_spring.models;

import java.math.BigDecimal;

import org.springframework.web.client.HttpServerErrorException.BadGateway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TGFPAR")

public class Parceiros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = true, length = 10, scale = 6)
    private BigDecimal weight;

    @Column(nullable = false, length = 2)
    private String symbol;
}
