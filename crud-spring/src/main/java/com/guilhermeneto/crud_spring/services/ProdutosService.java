package com.guilhermeneto.crud_spring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.guilhermeneto.crud_spring.dtos.ProdutoPageDto;
import com.guilhermeneto.crud_spring.dtos.ProdutoRequestDto;
import com.guilhermeneto.crud_spring.dtos.ProdutoResponseDto;
import com.guilhermeneto.crud_spring.dtos.mapper.ProdutoMapper;
import com.guilhermeneto.crud_spring.exceptions.RecordNotFound;
import com.guilhermeneto.crud_spring.models.Produtos;
import com.guilhermeneto.crud_spring.repository.ProdutosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Service
@Validated
public class ProdutosService {

    private final ProdutosRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutosService(ProdutosRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoPageDto getProdutos(@PositiveOrZero int page, @Positive @Max(100) int size, String name) {
        Page<Produtos> pageProduto;
        Pageable pageable = PageRequest.of(page, size);

        if (name != null && !name.trim().isEmpty() && !name.equalsIgnoreCase("\"\"")) {
            pageProduto = produtoRepository.findByDescrprodContainingIgnoreCase(name, pageable);
        } else {
            pageProduto = produtoRepository.findAll(pageable);
        }

        List<ProdutoResponseDto> produtos = pageProduto.getContent().stream()
                .map(produtoMapper::toDto)
                .collect(Collectors.toList());

        return new ProdutoPageDto(produtos, pageProduto.getTotalElements(), pageProduto.getTotalPages());
    }

    public ProdutoResponseDto getOne(@Valid Integer codprod) {
        return produtoRepository.findById(codprod).map(produtoMapper::toDto)
                .orElseThrow(() -> new RecordNotFound(codprod));
    }

    public ProdutoResponseDto save(@Valid @NotNull ProdutoRequestDto produtoDto) {

        Produtos entity = produtoMapper.toEntity(produtoDto);

        return produtoMapper.toDto(produtoRepository.save(entity));
    }

    public ProdutoResponseDto update(Integer codprod, @Valid @NotNull ProdutoRequestDto produtoDto) {
        return produtoRepository.findById(codprod)
                .map(recordFound -> {
                    // Atualiza dados do produto
                    recordFound.setDescrprod(produtoDto.descrprod());
                    recordFound.setCompldesc(produtoDto.compldesc());
                    recordFound.setCodvol(produtoDto.codvol());
                    recordFound.setEangtin(produtoDto.eangtin());
                    recordFound.setReferencia(produtoDto.eangtin());
                    recordFound.setDtcreated(produtoDto.dtcreated());
                    recordFound.setDtalter(produtoDto.dtalter());

                    return produtoRepository.save(recordFound);
                })
                .map(produtoMapper::toDto)
                .orElseThrow(() -> new RecordNotFound(codprod));               
    }

    public void delete(@Valid Integer codprod) {
        produtoRepository.delete(produtoRepository.findById(codprod)
                .orElseThrow(() -> new RecordNotFound(codprod)));
    }        
}
