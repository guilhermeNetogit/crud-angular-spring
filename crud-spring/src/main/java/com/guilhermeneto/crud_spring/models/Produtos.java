package com.guilhermeneto.crud_spring.models;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TGFPRO", schema = "dbo", catalog = "MY_DB_PROD")

public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODPROD")
    private Integer codprod;

    @NotNull
    @Length(min = 4, max = 50)
    @Column(name = "DESCRPROD", nullable = false, length = 50)
    private String descrprod;

    @Length(min = 4, max = 50)
    @Column(name = "COMPLDESC", nullable = true, length = 50)
    private String compldesc;

    @NotNull
    @Length(min = 2, max = 2)
    @Column(name = "CODVOL", nullable = false, length = 2)
    private String codvol;

    @NotNull
    @Length(min = 8, max = 14)
    @Column(name = "EANGTIN", nullable = false, length = 14)
    private String eangtin;

    @Length(min = 4, max = 14)
    @Column(name = "REFERENCIA", nullable = true, length = 14)
    private String referencia;

    @Column(name = "DTCREATED", nullable = true)
    private LocalDateTime dtcreated;
    
    @Column(name = "DTALTER", nullable = true)
    private LocalDateTime dtalter;

    public Integer getCodprod() {
        return codprod;
    }

    public void setCodprod(Integer codprod) {
        this.codprod = codprod;
    }

    public String getDescrprod() {
        return descrprod;
    }

    public void setDescrprod(String descrprod) {
        this.descrprod = descrprod;
    }

    public String getCompldesc() {
        return compldesc;
    }

    public void setCompldesc(String compldesc) {
        this.compldesc = compldesc;
    }

    public String getCodvol() {
        return codvol;
    }

    public void setCodvol(String codvol) {
        this.codvol = codvol;
    }

    public String getEangtin() {
        return eangtin;
    }

    public void setEangtin(String eangtin) {
        this.eangtin = eangtin;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public LocalDateTime getDtcreated() {
        return dtcreated;
    }

    public void setDtcreated(LocalDateTime dtcreated) {
        this.dtcreated = dtcreated;
    }

    public LocalDateTime getDtalter() {
        return dtalter;
    }

    public void setDtalter(LocalDateTime dtalter) {
        this.dtalter = dtalter;
    }

    
}
