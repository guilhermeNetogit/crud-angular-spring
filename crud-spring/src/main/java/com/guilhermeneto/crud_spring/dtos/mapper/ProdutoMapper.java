package com.guilhermeneto.crud_spring.dtos.mapper;

import org.springframework.stereotype.Component;

import com.guilhermeneto.crud_spring.dtos.ProdutoRequestDto;
import com.guilhermeneto.crud_spring.dtos.ProdutoResponseDto;
import com.guilhermeneto.crud_spring.models.Produtos;

@Component
public class ProdutoMapper {

    public ProdutoResponseDto toDto(Produtos produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoResponseDto(
                produto.getCodprod(),
                produto.getDescrprod(),
                produto.getCompldesc(),
                produto.getCodvol(),
                produto.getEangtin(),
                produto.getReferencia(),
                produto.getDtcreated(),
                produto.getDtalter());
    }

    public Produtos toEntity(ProdutoRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Produtos produto = new Produtos();
        applyDto(dto, produto);
        return produto;
    }

    public void updateEntity(ProdutoRequestDto dto, Produtos produto) {
        applyDto(dto, produto);
    }

    private void applyDto(ProdutoRequestDto dto, Produtos produto) {
        produto.setDescrprod(dto.descrprod());
        produto.setCompldesc(dto.compldesc());
        produto.setCodvol(dto.codvol());
        produto.setEangtin(dto.eangtin());
        produto.setReferencia(dto.referencia());
    }

}
