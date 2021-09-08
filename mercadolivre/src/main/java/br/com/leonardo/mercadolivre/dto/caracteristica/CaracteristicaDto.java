package br.com.leonardo.mercadolivre.dto.caracteristica;

import br.com.leonardo.mercadolivre.model.Caracteristica;

public class CaracteristicaDto {

        private Long id;
        private String nome;
        private String descricao;

        public CaracteristicaDto(Caracteristica caracteristica) {
            this.id = caracteristica.getId();
            this.nome = caracteristica.getNome();
            this.descricao = caracteristica.getDescricao();
        }


    public CaracteristicaDto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }
    }

