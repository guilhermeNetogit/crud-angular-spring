package com.guilhermeneto.crud_spring.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE TGFPAR SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status = 'Ativo'")
@Table(name = "TGFPAR")

public class Parceiros {

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

    @Builder.Default
    @Column(length = 8, nullable = false)
    @Convert(converter = StatusConverter.class)
    private StatusEnum status = StatusEnum.ATIVO;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parceiros")
    private List<Contatos> contatos = new ArrayList<>();
    
    @PrePersist
        public void ensureStatus() {
            if (this.status == null)
                this.status = StatusEnum.ATIVO;
        }
}
