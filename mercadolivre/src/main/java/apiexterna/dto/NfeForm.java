package apiexterna.dto;

import javax.validation.constraints.NotNull;

public class NfeForm {

    @NotNull(message = "Código da Compra")
    private Long idCompra;
    @NotNull(message = "Codigo do comprador")
    private Long idUsuarioDaCompra;


    public NfeForm(Long idCompra, Long idUsuarioDaCompra) {
        this.idCompra = idCompra;
        this.idUsuarioDaCompra = idUsuarioDaCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdUsuarioDaCompra() {
        return idUsuarioDaCompra;
    }
}
