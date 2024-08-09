package com.tools.challenge.models.pagamento;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {
    @Pattern(regexp = "^(AVISTA|PARCELADO LOJA|PARCELADO EMISSOR)$", message = "Tipo de pagamento inválido. Os valores permitidos são: AVISTA, PARCELADO LOJA, PARCELADO EMISSOR")
    @NotNull(message = "O campo tipo é obrigatório.")
    private String tipo;
    @Positive(message = "O valor minimo de parcelas deve ser 1")
    private int parcelas;

}
