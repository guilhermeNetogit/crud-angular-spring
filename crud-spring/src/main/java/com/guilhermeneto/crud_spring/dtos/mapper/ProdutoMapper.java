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
            produto.getDtalter()
        );
    }

    public Produtos toEntity(ProdutoRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Produtos produtos = new Produtos();
        
        produtos.setDescrprod(dto.descrprod());
        produtos.setCompldesc(dto.compldesc());
        produtos.setCodvol(dto.codvol());
        produtos.setEangtin(dto.eangtin());  
        produtos.setDtcreated(dto.dtcreated());  
        produtos.setDtalter(dto.dtalter());  
        
        return produtos;
    }
}
