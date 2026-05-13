package com.guilhermeneto.crud_spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guilhermeneto.crud_spring.models.Parceiros;

public interface ParceirosRepository extends JpaRepository<Parceiros, Integer> {
    Page<Parceiros> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
