package com.tools.challenge.models.pagamento.enums;

public enum StatusEnum {
    AUTORIZADO("AUTORIZADO"),
    CANCELADO("CANCELADO");
    private final String valor;

    StatusEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
