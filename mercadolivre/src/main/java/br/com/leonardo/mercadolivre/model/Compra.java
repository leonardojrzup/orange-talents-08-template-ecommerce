package br.com.leonardo.mercadolivre.model;

import apiexterna.ApiExterna;
import apiexterna.dto.NfeForm;
import apiexterna.dto.RankingForm;
import br.com.leonardo.mercadolivre.model.enuns.Status;
import br.com.leonardo.mercadolivre.model.enuns.StatusTransacao;
import br.com.leonardo.mercadolivre.excecao.RegraNegocioException;
import br.com.leonardo.mercadolivre.utils.EnviarEmail;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private LocalDateTime dataCompra;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario cliente;

    @NotNull(message = "Quantidade")
    @Positive(message = "Quantidade deve ser positiva")
    private Integer quantidade;

    @NotNull(message = "Status")
    private Status status;

    @ManyToOne
    private Produto produto;

    @NotNull(message = "Valor")
    private BigDecimal valor;

    @NotNull(message = "GatewayCompra")
    GatewayCompra gatewayCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private List<Transacao> transacoes = new ArrayList<>();


    @Deprecated
    public Compra(){

    }

    public Compra(Usuario cliente, Integer quantidade, Produto produto, GatewayCompra gatewayCompra) {
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.status = Status.INICIADA;
        this.produto = produto;
        this.valor = calcularValor(produto.getValor());
        this.gatewayCompra = gatewayCompra;
        this.dataCompra = LocalDateTime.now();
    }

    private BigDecimal calcularValor(BigDecimal valor) {
    return valor.multiply(BigDecimal.valueOf(quantidade));
    }

    public Long getId() {
        return Id;
    }

    public LocalDateTime getData() {
        return dataCompra;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Status getStatus() {
        return status;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public GatewayCompra getGatewayCompra() {
        return gatewayCompra;
    }



    public String urlRedirecionamento(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayCompra.criaUrlRetorno(this, uriComponentsBuilder);
    }


    public void adicionarTransacao(Transacao transacao, ApiExterna apiExterna) {
        if (this.transacoes.contains(transacao)) {
            throw new RegraNegocioException("Essa transação já aconteceu!");
        }
        List<Transacao> transacoesComSucesso = this.transacoes.stream().filter(transacao::concluidaComSucesso).collect(Collectors.toList());
        if (transacoesComSucesso.size() == 1)
            throw new RegraNegocioException("Essa compra ja possui uma transação de pagamento com sucesso!");

            processarComunicacaoSistemasExternos(transacao,  apiExterna );

        this.transacoes.add(transacao);


    }

    private void processarComunicacaoSistemasExternos(Transacao transacao, ApiExterna apiExterna ) {
        if (transacao.getStatus().equals(StatusTransacao.SUCESSO)) {
           apiExterna.comunicaSistemaNotaFiscal(new NfeForm(this.Id, this.cliente.getId()));
           apiExterna.comunicaSistemaRanking(new RankingForm(this.Id, this.produto.getVendedor().getId()));
            EnviarEmail.enviarEmailCompraSucesso(this.cliente, this.produto, this.quantidade);
        }
        else {
            EnviarEmail.enviarEmailCompraFalhou(this.cliente, this.produto, this.quantidade, this.gatewayCompra.toString(),this.getId());
        }
    }



}
