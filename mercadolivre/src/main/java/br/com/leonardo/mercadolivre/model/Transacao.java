package br.com.leonardo.mercadolivre.model;

import br.com.leonardo.mercadolivre.model.enuns.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Status da transação")
    private StatusTransacao status;
    @NotBlank(message = "Id da Transação")
    private  String idTransacaoGateway;
    @PastOrPresent(message = "Instante deve ser no passo ou presente")
    @NotNull(message = "Instante da criação da transação")
    private LocalDateTime instante;

    @NotNull(message = "Compra")
    @ManyToOne
    private Compra compra;


    @Deprecated
    public Transacao() {

    }

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public boolean concluidaComSucesso(Transacao transacao) {
        return this.status.equals(StatusTransacao.SUCESSO);
    }


    public Long getId() {
        return id;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public String getIdTransacaoGateway() {
        return idTransacaoGateway;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Compra getCompra() {
        return compra;
    }
}
