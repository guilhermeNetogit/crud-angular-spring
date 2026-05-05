package com.guilhermeneto.crud_spring.models;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TGFPAR")

public class Parceiros {

    @NotNull
    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive
    @Column(nullable = false)
    private Integer position;

    @NotNull
    @Length(min = 2, max = 30)
    @Column(nullable = false, length = 30)
    private String name;

    @Positive
    @Column(nullable = true, length = 10, scale = 6)
    private BigDecimal weight;

    @Column(nullable = false, length = 2)
    private String symbol;
}
