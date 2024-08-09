package com.tools.challenge.models.pagamento.enums;

public enum TipoPagamentoEnum {
    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");
    private final String valor;

    TipoPagamentoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
