package br.com.leonardo.mercadolivre.dto.retornogateway;

import br.com.leonardo.mercadolivre.model.Compra;
import br.com.leonardo.mercadolivre.model.RetornoGatewayPagamento;
import br.com.leonardo.mercadolivre.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoGatewayPagSeguro implements RetornoGatewayPagamento {


    @NotBlank(message = "Id da transação")
    private String idTransacao;
    @NotNull(message = "Status PagSeguro")
    private StatusRetornoPagSeguro status;

    @Override
    public Transacao toModel(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusRetornoPagSeguro getStatus() {
        return status;
    }
}
