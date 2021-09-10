package br.com.leonardo.mercadolivre.controller;


import apiexterna.ApiExterna;
import br.com.leonardo.mercadolivre.dto.retornogateway.RetornoGatewayPagSeguro;
import br.com.leonardo.mercadolivre.dto.retornogateway.RetornoGatewayPaypal;
import br.com.leonardo.mercadolivre.model.*;
import br.com.leonardo.mercadolivre.repository.CompraRepository;
import br.com.leonardo.mercadolivre.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ApiExterna apiExterna;


    @PostMapping
    @RequestMapping("/retorno-pagseguro/{id}")
    public String cadastrarTransacaoPagSeguro(@PathVariable("id") Long id, @RequestBody @Valid RetornoGatewayPagSeguro form) {
        return processo(id, form);
    }


    @PostMapping
    @RequestMapping("/retorno-paypal/{id}")
    public String cadastrarTransacaoPayPal(@PathVariable("id") Long id, @RequestBody @Valid RetornoGatewayPaypal form) {
        return processo(id, form);
    }


    private String processo(Long idCompra, RetornoGatewayPagamento form) {
        Optional<Compra> compra = compraRepository.findById(idCompra);
        if (compra.isEmpty())
            throw new IllegalArgumentException("Compra não encontrada no banco de dados");
        Compra compraConfirmada = compra.get();
        Transacao transacao = form.toModel(compraConfirmada);

        compraConfirmada.adicionarTransacao(transacao, apiExterna);
        compraConfirmada = compraRepository.save(compraConfirmada);
        System.out.println("Conexão realizada com sucesso");
        return compra.toString();
    }

}
