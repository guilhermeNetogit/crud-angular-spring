package com.guilhermeneto.crud_spring.exceptions;

public class RecordNotFound extends RuntimeException {
    
    public RecordNotFound(Integer id) {
		super("O registro com id " + id + " não foi encontrado");
	}
}
