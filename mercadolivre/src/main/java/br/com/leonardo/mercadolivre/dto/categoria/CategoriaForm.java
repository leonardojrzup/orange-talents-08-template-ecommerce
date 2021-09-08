package br.com.leonardo.mercadolivre.dto.categoria;

import br.com.leonardo.mercadolivre.annotations.Unique;
import br.com.leonardo.mercadolivre.model.Categoria;
import br.com.leonardo.mercadolivre.repository.CategoriaRepository;
import org.springframework.util.Assert;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CategoriaForm {

    @Unique(fieldName = "nome", domainClass = Categoria.class, message = "Categoria já cadastrada no banco de dados")
    @NotBlank(message = "Nome da categoria")
    @NotNull(message = "Nome da categoria")
    private String nome;

    private Long categoriaMae;

    @Deprecated
    public CategoriaForm() {

    }

    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    public CategoriaForm(String nome, Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        if (categoriaMae != null) {
            Optional<Categoria> resultado = categoriaRepository.findById(categoriaMae);
            Assert.isTrue(!resultado.isEmpty(), "Id da categoria Mãe não existe no banco de dados");
            return new Categoria(nome, resultado.get());
        } else {
            return new Categoria(nome);
        }
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMae() {
        return categoriaMae;
    }
}
