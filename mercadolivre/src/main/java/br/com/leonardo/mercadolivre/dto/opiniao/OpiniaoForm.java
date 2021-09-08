package br.com.leonardo.mercadolivre.dto.opiniao;

import br.com.leonardo.mercadolivre.excecao.RegraNegocioException;
import br.com.leonardo.mercadolivre.model.Opiniao;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.repository.ProdutoRepository;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class OpiniaoForm {

    @Min(value = 1,message = "Nota deve ser no minimo 1")
    @Max(value = 5,message = "Nota deve ser no máximo 5")
    @NotNull(message = "Nota")
    private Integer nota;

    @NotBlank(message = "titulo")
    private String titulo;

    @NotBlank(message = "Descrição")
    @Length(max = 500,message = "Descrição deve possuir no máximo 500 caracteres")
    private String descricao;


    @Deprecated
    public OpiniaoForm() {

    }

    public OpiniaoForm(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Long id, ProdutoRepository repository, Usuario usuario) {
        Optional<Produto> produto = repository.findById(id);
       if (produto.isEmpty()) throw new RegraNegocioException("id-" + id +" - Não existe um produto com esse id.");
        return new Opiniao(this.nota, this.titulo, this.descricao, usuario);
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
