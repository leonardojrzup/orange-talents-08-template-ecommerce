package br.com.leonardo.mercadolivre.model;


//Interface Generica
public interface RetornoGatewayPagamento {

    Transacao toModel(Compra compra);
};

