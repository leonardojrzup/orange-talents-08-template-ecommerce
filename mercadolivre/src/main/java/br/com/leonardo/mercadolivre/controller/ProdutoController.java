package br.com.leonardo.mercadolivre.controller;

import br.com.leonardo.mercadolivre.dto.FotosProdutos.FotosProdutosForm;
import br.com.leonardo.mercadolivre.dto.pergunta.PerguntaForm;
import br.com.leonardo.mercadolivre.dto.opiniao.OpiniaoForm;
import br.com.leonardo.mercadolivre.dto.produto.ProdutoDTO;
import br.com.leonardo.mercadolivre.dto.produto.DetalharProduto;
import br.com.leonardo.mercadolivre.dto.produto.ProdutoForm;
import br.com.leonardo.mercadolivre.utils.EnviarEmail;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.repository.CategoriaRepository;
import br.com.leonardo.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


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
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Esse produto não pertece a esse usuario");
        } else {
            produto.adicionarImagens(form.toModel());
            produtoRepository.save(produto);
        }
    }

    @PostMapping
    @RequestMapping("/{id}/opiniao")
    public void adicionarOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoForm form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            Produto produtoConfirmado = produto.get();
            produtoConfirmado.adicionarOpiniao(form.toModel(id, produtoRepository, logado));
            produtoRepository.save(produtoConfirmado);
        }

    }

    @PostMapping
    @RequestMapping("/{id}/perguntas")
    public void adicionarPerguntas(@PathVariable("id") Long id, @RequestBody @Valid PerguntaForm form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            Produto produtoConfirmado = produto.get();
            produtoConfirmado.adicionarPerguntas(form.toModel(id, produtoRepository, logado));
            produtoRepository.save(produtoConfirmado);
            EnviarEmail.enviarEmailNovaPergunta(produtoConfirmado.getVendedor(), logado, produtoConfirmado);
        }
    }

    @GetMapping
    @RequestMapping("/{id}")
    public DetalharProduto detalhar(@PathVariable("id") Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            Produto produtoConfirmado = produto.get();
            return new DetalharProduto(produtoConfirmado);
        }
    }
}

