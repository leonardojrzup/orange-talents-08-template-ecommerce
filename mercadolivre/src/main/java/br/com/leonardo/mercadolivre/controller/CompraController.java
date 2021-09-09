package br.com.leonardo.mercadolivre.controller;


import br.com.leonardo.mercadolivre.dto.venda.CompraForm;
import br.com.leonardo.mercadolivre.model.*;
import br.com.leonardo.mercadolivre.repository.CompraRepository;
import br.com.leonardo.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private EnviarEmail enviar;

    @PostMapping
    @Transactional
    public String cadastrarCompra(@RequestBody @Valid CompraForm request, UriComponentsBuilder uriComponentsBuilder) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Optional<Produto> produto = produtoRepository.findById(request.getIdProduto());
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            Produto produtoConfirmado = produto.get();
            if (produtoConfirmado.getQuantidade() < 1)
                throw new IllegalArgumentException("O estoque do produto " + produtoConfirmado.getNome() + " está zerado.");
            Compra compra = request.toModel(produtoConfirmado, logado);
            if (produtoConfirmado.AbaterEstoque(request.getQuantidade())) {
                produtoRepository.save(produtoConfirmado);
                compraRepository.save(compra);
                EnviarEmail.enviarEmailNovaCompra(produtoConfirmado.getVendedor(), logado, produtoConfirmado);
                if (compra.getGatewayCompra().equals(GatewayCompra.Paypal)) {
                    String urlRedirect = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                            .buildAndExpand(compra.getId().toString()).toString();
                    return urlRedirect;
                } else {
                    String urlRedirect = uriComponentsBuilder.path("/retorno-paypal/{id}")
                            .buildAndExpand(compra.getId().toString()).toString();
                    return urlRedirect;
                }
            } else {
                throw new IllegalArgumentException("O estoque do produto " + produtoConfirmado.getNome() + " é menor que a quantidade na compra");
            }
        }
    }
}
