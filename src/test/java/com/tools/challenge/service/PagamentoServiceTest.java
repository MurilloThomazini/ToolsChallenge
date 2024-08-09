package com.tools.challenge.service;

import com.tools.challenge.models.pagamento.Transacao;
import com.tools.challenge.models.pagamento.Descricao;
import com.tools.challenge.models.pagamento.FormaPagamento;
import com.tools.challenge.models.pagamento.TransacaoResponse;
import com.tools.challenge.models.pagamento.enums.StatusEnum;
import com.tools.challenge.models.pagamento.enums.TipoPagamentoEnum;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PagamentoServiceTest {

    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        pagamentoService = new PagamentoService();
    }

    @Test
    void testSalvarTransacaoValida() throws BadRequestException {
        Transacao transacao = criarTransacao();

        pagamentoService.salvarTransacao(transacao);

        TransacaoResponse response = pagamentoService.consultarPorId(transacao.getId());
        assertEquals(StatusEnum.AUTORIZADO, response.getTransacao().getDescricao().getStatus());
    }

    @Test
    void testSalvarTransacaoDuplicada() throws BadRequestException {
        Transacao transacao = criarTransacao();
        pagamentoService.salvarTransacao(transacao);

        assertThrows(BadRequestException.class, () -> pagamentoService.salvarTransacao(transacao));
    }

    @Test
    void testValidarEntradaPagamento_Invalida() {
        Transacao transacao = criarTransacao();
        transacao.getFormaPagamento().setTipo(TipoPagamentoEnum.AVISTA.getValor());
        transacao.getFormaPagamento().setParcelas(2);

        assertThrows(BadRequestException.class, () -> pagamentoService.salvarTransacao(transacao));
    }

    private Transacao criarTransacao() {
        Descricao descricao = new Descricao();
        FormaPagamento formaPagamento = new FormaPagamento(TipoPagamentoEnum.PARCELADO_LOJA.getValor(), 3);

        return new Transacao("4123234532211234", "10001231231237", descricao, formaPagamento);
    }
}