package br.com.leonardo.mercadolivre.dto.venda;

import br.com.leonardo.mercadolivre.annotations.Exists;
import br.com.leonardo.mercadolivre.model.GatewayCompra;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.model.Compra;
import br.com.leonardo.mercadolivre.repository.ProdutoRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Optional;

public class CompraForm {


    @NotNull(message = "Quantidade")
    @Positive(message = "Quantidade deve ser maior que 1")
    private Integer quantidade;

    @NotNull(message = "Id pro produto")
    @Exists(domainClass = Produto.class, fieldName = "id", message = "Produto não encontrado no banco de dados")
    private Long idProduto;

    @NotNull(message = "Metodo de pagamento")
    private GatewayCompra gatewayCompra;

    public CompraForm(Integer quantidade, Long idProduto, GatewayCompra gatewayCompra) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gatewayCompra = gatewayCompra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayCompra getGatewayCompra() {
        return gatewayCompra;
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        BigDecimal valor = produto.getValor() .multiply(BigDecimal.valueOf(this.quantidade));
        return new Compra(usuario, quantidade, produto, gatewayCompra );
    }

}
