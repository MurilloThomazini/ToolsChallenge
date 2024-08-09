package com.tools.challenge.controller;

import com.tools.challenge.models.pagamento.TransacaoResponse;
import com.tools.challenge.service.PagamentoService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<TransacaoResponse> solicitarPagamento(@Valid @RequestBody TransacaoResponse objectDTO) throws BadRequestException {
        pagamentoService.salvarTransacao(objectDTO.getTransacao());
        return ResponseEntity.ok(objectDTO);
    }
    @PostMapping("/{id}/estorno")
    public ResponseEntity<TransacaoResponse> estornarPagamento(@PathVariable String id) throws BadRequestException {
        return ResponseEntity.ok(pagamentoService.estornarPagamento(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponse> consultarPorId(@PathVariable String id) throws BadRequestException {
        return ResponseEntity.ok(pagamentoService.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponse>> consultarTodos() {
        return ResponseEntity.ok(pagamentoService.consultarTodos());
    }
}
