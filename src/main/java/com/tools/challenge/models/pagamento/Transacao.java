package com.tools.challenge.models.pagamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @NotNull(message = "O campo cartão é obrigatório")
    @Size(min=16, max=16, message = "O campo cartão deve conter 16 digitos")
    private String cartao;
    @NotNull(message = "O campo id é obrigatório")
    @Size(min=1, message = "O campo id não pode estar vazio")
    private String id;
    @NotNull(message = "Os campos da descrição são obrigatórios")
    @Valid
    private Descricao descricao;
    @NotNull(message = "Os campos da formaPagamento são obrigatórios")
    @Valid
    private FormaPagamento formaPagamento;
}
