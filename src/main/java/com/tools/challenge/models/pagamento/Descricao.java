package com.tools.challenge.models.pagamento;

import com.tools.challenge.models.pagamento.enums.StatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Descricao {
    @NotNull(message = "O campo valor é obrigatório.")
    @Positive(message = "O campo valor deve ser maior que zero.")
    private Double valor;
    @NotNull(message = "O campo dataHora é obrigatório.")
    @Size(min = 1, message = "O campo dataHora não pode ser vazio")
    private String dataHora;
    @NotNull(message = "O campo estabelecimento é obrigatório.")
    @Size(min = 1, message = "O campo estabelecimento não pode ser vazio")
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusEnum status;
}
