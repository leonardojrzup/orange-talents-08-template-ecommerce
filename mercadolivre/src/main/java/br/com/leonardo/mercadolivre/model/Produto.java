package br.com.leonardo.mercadolivre.model;

import br.com.leonardo.mercadolivre.dto.caracteristica.CaracteristicaForm;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Nome")
    private String nome;

    @NotNull(message = "Valor")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "Quantidade")
    @Positive(message = "Quantidade deve ser positivo")
    private  int quantidade;

    @NotBlank(message = "Descricao")
    @Length(max = 1000)
    private  String descricao;

    @NotNull(message = "Categoria")
    @Valid
    @ManyToOne
    private Categoria categoria;

    @NotNull
    private LocalDateTime dataCadastro;


    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();

    @ManyToOne
    private Usuario vendedor;


    @OneToMany(cascade = CascadeType.PERSIST)
    private List<FotosProdutos> fotos = new ArrayList<FotosProdutos>();




    public Produto( String nome, BigDecimal valor, int quantidade, String descricao, Categoria categoria, List<CaracteristicaForm> caracteristicas, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataCadastro = LocalDateTime.now();
        this.vendedor = usuario;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel())
                .collect(Collectors.toSet()));
    }



    @Deprecated
    public Produto() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public boolean pertenceAoUsuario(Usuario logado) {
            return this.vendedor.equals(logado);
        }
}
