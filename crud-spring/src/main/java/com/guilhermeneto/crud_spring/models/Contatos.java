package com.guilhermeneto.crud_spring.models;

import org.hibernate.validator.constraints.Length;

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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TGFCONT", schema = "dbo", catalog = "MY_DB_PROD")
public class Contatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codcontato;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 30)
    @Column(nullable = false, length = 30)
    private String nomecontato;

    @Length(min = 7, max = 15)
    @Column(length = 15)
    private String telefone;

    private String email;

    private String siteurl;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codparc_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Parceiros parceiros;

    public Integer getCodcontato() {
        return codcontato;
    }
    public void setCodcontato(Integer codcontato) {
        this.codcontato = codcontato;
    }

    public String getNomecontato() {
        return nomecontato;
    }
    public void setNomecontato(String nomecontato) {
        this.nomecontato = nomecontato;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteurl() {
        return siteurl;
    }
    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public Parceiros getParceiros() {
        return parceiros;
    }
    public void setParceiros(Parceiros parceiros) {
        this.parceiros = parceiros;
    }

    @Override
    public String toString() {
        return "Contatos [codcontato=" + codcontato + ", nomecontato=" + nomecontato + ", telefone=" + telefone
                + ", email=" + email + ", siteurl=" + siteurl + ", parceiros=" + parceiros + "]";
    }

}
