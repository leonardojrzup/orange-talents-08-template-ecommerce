package br.com.leonardo.mercadolivre.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class FotosProdutos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Posi
    @NotNull(message = "Link")
    private String link;

    @Deprecated
    public FotosProdutos() {

    }

    public FotosProdutos(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public Long getId() {
        return id;
    }

}
