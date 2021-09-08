package br.com.leonardo.mercadolivre.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1,message = "Nota deve ser no minimo 1")
    @Max(value = 5,message = "Nota deve ser no máximo 5")
    @NotNull(message = "Nota")
    private Integer nota;

    @NotBlank(message = "titulo")
    private String titulo;

    @NotBlank(message = "Descrição")
    @Length(max = 500,message = "Descrição deve possuir no máximo 500 caracteres")
    private String descricao;

    @ManyToOne
    @NotNull(message = "Usuario")
    private Usuario usuario;


    @Deprecated
    public Opiniao(){

    }

    public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
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

    public Usuario getUsuario() {
        return usuario;
    }
}
