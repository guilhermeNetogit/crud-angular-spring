package com.guilhermeneto.crud_spring.enums;

public enum StatusEnum {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String value;

    private StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
            return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
