package br.com.leonardo.mercadolivre.dto.pergunta;

import br.com.leonardo.mercadolivre.excecao.RegraNegocioException;
import br.com.leonardo.mercadolivre.model.Pergunta;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.repository.ProdutoRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class PerguntaForm {


    @NotBlank(message = "Titulo")
    private String titulo;

    public Pergunta toModel(Long id, ProdutoRepository repository, Usuario usuario) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isEmpty()) throw new RegraNegocioException("O produto informado não existe no banco de dados");
        return new Pergunta(this.titulo, usuario);
    }

    public String getTitulo() {
        return titulo;
    }
}
