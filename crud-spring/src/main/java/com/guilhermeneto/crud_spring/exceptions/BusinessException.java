package com.guilhermeneto.crud_spring.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem, Object ... params) {
        super(String.format(mensagem, params));
    }
}
