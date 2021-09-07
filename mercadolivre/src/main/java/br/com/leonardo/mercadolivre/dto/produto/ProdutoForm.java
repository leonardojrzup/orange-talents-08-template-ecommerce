package br.com.leonardo.mercadolivre.dto.produto;



import br.com.leonardo.mercadolivre.annotations.Exists;
import br.com.leonardo.mercadolivre.annotations.Unique;
import br.com.leonardo.mercadolivre.dto.caracteristica.CaracteristicaForm;
import br.com.leonardo.mercadolivre.model.Caracteristica;
import br.com.leonardo.mercadolivre.model.Categoria;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoForm {

        @NotBlank(message = "Nome")
        @Unique(fieldName = "nome", domainClass = Produto.class, message = "Produto já cadastrado no banco de dados")
        private String nome;

        @Positive(message = "Valor deve ser positivo")
        @NotNull(message = "Quantidade")
        private Integer quantidade;

        @NotBlank(message = "Descrição")
        @Length(max = 1000)
        private String descricao;

        @NotNull(message = "Valor")
        @Positive(message = "Valor deve ser positivo")
        private BigDecimal valor;

        @NotNull(message = "idCategoria")
        @Exists(domainClass = Categoria.class, fieldName = "id", message = "Essa categoria não existe.")
        private Long idCategoria;

        @NotNull(message = "Caracteristica")
        @Size(min = 3, message = "A quantidade miníma de caracteristicas é 3.")
        @Valid
        private List<CaracteristicaForm> caracteristicas;



    public Produto toModel(CategoriaRepository categoriaRepository, Usuario user) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow();


        return new Produto(nome, valor, quantidade, descricao, categoria,caracteristicas, user);
    }


        public String getNome() {
                return nome;
        }

        public Integer getQuantidade() {
                return quantidade;
        }

        public String getDescricao() {
                return descricao;
        }

        public BigDecimal getValor() {
                return valor;
        }

        public Long getIdCategoria() {
                return idCategoria;
        }

        public List<CaracteristicaForm> getCaracteristicas() {
                return caracteristicas;
        }
}
