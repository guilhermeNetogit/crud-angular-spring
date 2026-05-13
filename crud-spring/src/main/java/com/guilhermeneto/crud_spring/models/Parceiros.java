package com.guilhermeneto.crud_spring.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.guilhermeneto.crud_spring.enums.StatusEnum;
import com.guilhermeneto.crud_spring.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@SQLDelete(sql = "UPDATE TGFPAR SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status = 'Ativo'")
@Table(name = "TGFPAR", schema = "dbo", catalog = "MY_DB_PROD")

public class Parceiros {

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
    @Column(nullable = true, length = 10, scale = 2)
    private BigDecimal weight;

    @NotNull
    @Length(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String symbol;

    @Column(length = 8, nullable = false)
    @Convert(converter = StatusConverter.class)
    private StatusEnum status = StatusEnum.ATIVO;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parceiros")
    private List<Contatos> contatos = new ArrayList<>();

    public Parceiros() {}

    public Parceiros(Integer position, String name, BigDecimal weight, String symbol) {
        this.position = position;
        this.name = name;
        this.weight = weight;
        this.symbol = symbol;
        this.status = StatusEnum.ATIVO;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    public List<Contatos> getContatos() {
        return contatos;
    }
    public void setContatos(List<Contatos> novosContatos) {
        if (this.contatos == null) {
            this.contatos = new ArrayList<>();
        }
        
        this.contatos.clear();

        if (novosContatos != null) {
            novosContatos.forEach(contato -> {
                contato.setParceiros(this);
                this.contatos.add(contato);
            });
        }
    }

    @PrePersist
    public void ensureStatus() {
        if (this.status == null)
            this.status = StatusEnum.ATIVO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parceiros parceiros = (Parceiros) o;
        return Objects.equals(id, parceiros.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
