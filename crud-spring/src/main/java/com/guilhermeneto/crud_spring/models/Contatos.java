package com.guilhermeneto.crud_spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "TGFCONT")
public class Contatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codcontato;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String nomecontato;

    @Column(length = 20)
    private String telefone;

    private String email;

    private String siteurl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codparc_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Parceiros parceiros;
}
