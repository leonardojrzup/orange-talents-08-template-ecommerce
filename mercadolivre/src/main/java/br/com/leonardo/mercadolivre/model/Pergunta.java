package br.com.leonardo.mercadolivre.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titulo")
    private String titulo;

    private LocalDateTime instanteCricacao = LocalDateTime.now();

    @ManyToOne
    @NotNull(message = "Usuario")
    private Usuario usuario;

    public Pergunta() {
    }

    public Pergunta(String titulo, Usuario usuario) {
        this.titulo = titulo;
        this.instanteCricacao = LocalDateTime.now();
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCricacao() {
        return instanteCricacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
