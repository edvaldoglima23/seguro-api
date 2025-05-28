package com.example.seguro.model;

public enum TipoSeguro {
    AUTO("Automóvel"),
    VIDA("Vida"),
    RESIDENCIAL("Residencial"),
    EMPRESARIAL("Empresarial"),
    SAUDE("Saúde"),
    VIAGEM("Viagem");

    private final String descricao;

    TipoSeguro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
} 