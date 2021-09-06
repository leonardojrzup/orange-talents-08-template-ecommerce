package br.com.leonardo.mercadolivre.controller;

import br.com.leonardo.mercadolivre.dto.produto.ProdutoDTO;
import br.com.leonardo.mercadolivre.dto.produto.ProdutoForm;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.repository.CaracteristicaRepository;
import br.com.leonardo.mercadolivre.repository.CategoriaRepository;
import br.com.leonardo.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ProdutoDTO Salvar(@RequestBody @Valid ProdutoForm request) {
        Produto produto = request.toModel(categoriaRepository);
        produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getValor(), produto.getQuantidade(),
                produto.getDescricao(), produto.getCategoria(), produto.getCaracteristicas(), produto.getDataCadastro());
    }

}
