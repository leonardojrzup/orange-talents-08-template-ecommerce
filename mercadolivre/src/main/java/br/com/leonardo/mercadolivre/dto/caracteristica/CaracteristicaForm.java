package br.com.leonardo.mercadolivre.dto.caracteristica;

import br.com.leonardo.mercadolivre.model.Caracteristica;
import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.repository.CaracteristicaRepository;

import javax.validation.constraints.NotBlank;

public class CaracteristicaForm {


        @NotBlank
        private String nome;

        @NotBlank
        private String descricao;

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }

    public Caracteristica toModel() {
            return new Caracteristica(nome, descricao);
    }
}


