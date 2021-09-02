package br.com.leonardo.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String login;
    private String senha;
    @PastOrPresent
    private LocalDateTime dataCriacao;

    @Deprecated
 public Usuario(){}

    public Usuario( String login, String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);;
        this.dataCriacao = LocalDateTime.now();
    }


    public Long getId() {
        return Id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
