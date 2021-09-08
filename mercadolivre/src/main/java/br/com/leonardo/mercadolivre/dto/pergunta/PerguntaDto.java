package br.com.leonardo.mercadolivre.dto.pergunta;

import br.com.leonardo.mercadolivre.model.Pergunta;

import java.time.LocalDateTime;

public class PerguntaDto {


    private Long id;
    private String usuario;
    private String titulo;
    private LocalDateTime instanteCriacao;


    public PerguntaDto(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.usuario = pergunta.getUsuario().getLogin();
        this.titulo = pergunta.getTitulo();
        this.instanteCriacao = pergunta.getInstanteCricacao();
    }

    public PerguntaDto(Long id, String usuario, String titulo, LocalDateTime instanteCriacao) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.instanteCriacao = instanteCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }
}
