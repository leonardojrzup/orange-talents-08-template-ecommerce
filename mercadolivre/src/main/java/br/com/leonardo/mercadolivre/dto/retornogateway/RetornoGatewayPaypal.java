package br.com.leonardo.mercadolivre.dto.retornogateway;

import br.com.leonardo.mercadolivre.model.Compra;
import br.com.leonardo.mercadolivre.model.RetornoGatewayPagamento;
import br.com.leonardo.mercadolivre.model.enuns.StatusTransacao;
import br.com.leonardo.mercadolivre.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoGatewayPaypal implements RetornoGatewayPagamento {
    @NotBlank(message = "Id da transação")
    private String idTransacao;
    @Min(value = 0,message = "Valor minimo é 0")
    @Max(value = 1,message ="Valor máximo é 1" )
    private int status;

    public RetornoGatewayPaypal(String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }


    public Transacao toModel(Compra compra) {
        final StatusTransacao statusTransacao = this.status == 0 ? StatusTransacao.ERRO : StatusTransacao.SUCESSO;
        return new Transacao(statusTransacao, idTransacao, compra);
    }

    @Override
    public String toString() {
        return "RetornoPagSeguroForm{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public int getStatus() {
        return status;
    }
}