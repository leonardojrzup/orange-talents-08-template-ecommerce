package br.com.leonardo.mercadolivre.dto.produto;

import br.com.leonardo.mercadolivre.dto.caracteristica.CaracteristicaDto;
import br.com.leonardo.mercadolivre.dto.opiniao.OpiniaoDto;
import br.com.leonardo.mercadolivre.dto.pergunta.PerguntaDto;
import br.com.leonardo.mercadolivre.model.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalharProduto {

    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Double mediaNotas;
    private Integer totalOpinioes;
    private List<CaracteristicaDto> caracteristicas = new ArrayList<>();
    private List<String> linksParaImagens = new ArrayList<>();
    private List<OpiniaoDto> opinioes = new ArrayList<>();
    private List<PerguntaDto> perguntas = new ArrayList<>();


    public DetalharProduto(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        preencherCaracteristicas(produto);
        preencherLinksImagens(produto);
        preencherMediaETotalOpinioes(produto);
        preencherOpinioes(produto);
        preencherPerguntas(produto);
    }

    private void preencherPerguntas(Produto produto) {
        produto.getPerguntas().forEach(pergunta -> {
            PerguntaDto perguntaDTO = new PerguntaDto(pergunta);
            this.perguntas.add(perguntaDTO);
        });
    }


    private void preencherOpinioes(Produto produto) {
        produto.getOpinioes().forEach(opiniao -> {
            OpiniaoDto opiniaoDTO = new OpiniaoDto(opiniao);
            this.opinioes.add(opiniaoDTO);
        });
    }

    private void preencherMediaETotalOpinioes(Produto produto) {
        double soma = 0;
        for(int i = 0; i < produto.getOpinioes().size(); i++) {
            soma = soma + produto.getOpinioes().get(i).getNota();
        }
        mediaNotas = soma/produto.getOpinioes().size();
        totalOpinioes = produto.getOpinioes().size();

    }
    private void preencherCaracteristicas(Produto produto) {
        produto.getCaracteristicas().forEach(caracteristica -> {
            CaracteristicaDto caracteristicaDTO = new CaracteristicaDto(caracteristica);
            this.caracteristicas.add(caracteristicaDTO);
        });
    }


    private void preencherLinksImagens(Produto produto) {
        produto.getFotos().forEach(imagem -> {
            linksParaImagens.add(imagem.getLink());
        });
    }
    public List<String> getLinksParaImagens() {
        return linksParaImagens;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer totalOpinioes() {
        return totalOpinioes;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }
}