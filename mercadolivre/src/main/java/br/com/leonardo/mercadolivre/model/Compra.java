package br.com.leonardo.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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


}
