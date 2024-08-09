package com.tools.challenge.controller;

import com.tools.challenge.models.pagamento.Descricao;
import com.tools.challenge.models.pagamento.FormaPagamento;
import com.tools.challenge.models.pagamento.Transacao;
import com.tools.challenge.models.pagamento.TransacaoResponse;
import com.tools.challenge.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void solicitarPagamentoTest() throws Exception {
        Transacao transacao = new Transacao();
        TransacaoResponse response = new TransacaoResponse();
        response.setTransacao(transacao);

        ResponseEntity<TransacaoResponse> result = pagamentoController.solicitarPagamento(response);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void estornarPagamentoTest() throws Exception {
        String id = "1001";
        TransacaoResponse response = new TransacaoResponse();

        when(pagamentoService.estornarPagamento(id)).thenReturn(response);

        ResponseEntity<TransacaoResponse> result = pagamentoController.estornarPagamento(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void consultarPorIdTest() throws Exception {
        String id = "1001";
        TransacaoResponse response = new TransacaoResponse();

        when(pagamentoService.consultarPorId(id)).thenReturn(response);

        ResponseEntity<TransacaoResponse> result = pagamentoController.consultarPorId(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void consultarTodosTest() {

        TransacaoResponse response = new TransacaoResponse();
        List<TransacaoResponse> responseList = Collections.singletonList(response);

        when(pagamentoService.consultarTodos()).thenReturn(responseList);

        ResponseEntity<List<TransacaoResponse>> result = pagamentoController.consultarTodos();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseList, result.getBody());
    }
}