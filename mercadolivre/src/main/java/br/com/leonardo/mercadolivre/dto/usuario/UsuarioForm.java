package br.com.leonardo.mercadolivre.dto.usuario;

import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.annotations.Unique;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioForm {

    @NotNull(message = "Login")
    @NotBlank(message = "Login")
    @Email(message = "Login")
    @Unique(fieldName = "login", domainClass = Usuario.class, message = "login")
    private String login;

    @Length(min = 6,message = "Senha")
    @NotNull(message = "Senha")
    @NotBlank(message = "Senha")
    private String senha;


    public UsuarioForm(@Email @NotBlank String login,
                       @NotBlank  @Min(value = 6)String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsuarioForm(){
    }


    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login,this.senha);
    }

}
