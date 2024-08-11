package com.tools.challenge.service;

import com.tools.challenge.exception.TransacaoNaoEncontradaException;
import com.tools.challenge.models.pagamento.TransacaoResponse;
import com.tools.challenge.models.pagamento.Transacao;
import com.tools.challenge.models.pagamento.enums.StatusEnum;
import com.tools.challenge.models.pagamento.enums.TipoPagamentoEnum;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
public class PagamentoService{
    private final Map<String, Transacao> transacoes = new HashMap<>();
    private static final SecureRandom random = new SecureRandom();

    public void salvarTransacao(Transacao transacao) throws BadRequestException {
        validarEntradaPagamento(transacao);

        String primeirosDigitos = transacao.getCartao().substring(0, 4);
        String ultimosDigitos = transacao.getCartao().substring(12, 16);
        transacao.setCartao(primeirosDigitos + "********" + ultimosDigitos);

        transacao.getDescricao().setNsu(gerarValorUnico());
        transacao.getDescricao().setCodigoAutorizacao(gerarValorUnico());
        transacao.getDescricao().setStatus(StatusEnum.AUTORIZADO);
        transacoes.put(transacao.getId(), transacao);
    }

    public TransacaoResponse estornarPagamento(String id) {
        Transacao transacaoConsultada = consultarPorId(id).getTransacao();
        transacaoConsultada.getDescricao().setStatus(StatusEnum.CANCELADO);
        transacoes.put(id, transacaoConsultada);
        return new TransacaoResponse(transacaoConsultada);
    }

    public TransacaoResponse consultarPorId(String id) throws TransacaoNaoEncontradaException {
        Transacao transacao = transacoes.get(id);
        if (transacao == null) {
            throw new TransacaoNaoEncontradaException("Transação com o ID " + id + " não existe");
        }
        return new TransacaoResponse(transacao);
    }

    public List<TransacaoResponse> consultarTodos() {
        List<Transacao> lista = new ArrayList<>(transacoes.values());
        List<TransacaoResponse> listaRetorno = new ArrayList<>();
        if (lista.isEmpty()) {
            throw new TransacaoNaoEncontradaException("Não existem transações para serem listadas.");
        }
        lista.forEach(transacao -> listaRetorno.add(new TransacaoResponse(transacao)));
        return listaRetorno;
    }

    private String gerarValorUnico() {
        int length = 12;
        StringBuilder valor = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int digito = random.nextInt(10);
            valor.append(digito);
        }
        return valor.toString();
    }

    private void validarEntradaPagamento(Transacao transacao) throws BadRequestException {
        if (transacoes.containsKey(transacao.getId())) {
            throw new BadRequestException("Transação com o ID " + transacao.getId() + " já existe");
        }
        if (transacao.getFormaPagamento().getTipo().equals(TipoPagamentoEnum.AVISTA.getValor()) && transacao.getFormaPagamento().getParcelas() > 1) {
            throw new BadRequestException("Forma de pagamento invalida: Pagamentos a vista devem conter apenas uma parcela");
        } else if ((!transacao.getFormaPagamento().getTipo().equals(TipoPagamentoEnum.AVISTA.getValor()) && transacao.getFormaPagamento().getParcelas() == 1)) {
            throw new BadRequestException("Forma de pagamento invalida: Pagamentos parcelados devem conter mais do que uma parcela");
        }
    }
}
