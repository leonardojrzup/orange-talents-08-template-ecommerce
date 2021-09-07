package br.com.leonardo.mercadolivre.controller;

import br.com.leonardo.mercadolivre.dto.FotosProdutos.FotosProdutosForm;
import br.com.leonardo.mercadolivre.dto.produto.ProdutoDTO;
import br.com.leonardo.mercadolivre.dto.produto.ProdutoForm;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.repository.CaracteristicaRepository;
import br.com.leonardo.mercadolivre.repository.CategoriaRepository;
import br.com.leonardo.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

@GetMapping
    public List<Produto> Listar(){
    return (List<Produto>) produtoRepository.findAll();
}

    @PostMapping

    public ProdutoDTO Salvar(@RequestBody @Valid ProdutoForm request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Produto produto = request.toModel(categoriaRepository, logado);
        produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getValor(), produto.getQuantidade(),
                produto.getDescricao(), produto.getCategoria(), produto.getCaracteristicas(), produto.getDataCadastro());
    }


    @PostMapping
    @RequestMapping("/{id}/imagens")
    public void adicionarImagens(@PathVariable("id") Long id, @Valid FotosProdutosForm form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Produto produto = produtoRepository.findById(id).orElseThrow();
        if (!produto.pertenceAoUsuario(logado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Esse produto não pertece a esse usuario");
        } else {
            produto.adicionarImagens(form.toModel());
            produtoRepository.save(produto);
        }

    }
}