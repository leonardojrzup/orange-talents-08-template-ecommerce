package apiexterna.dto;

import javax.validation.constraints.NotNull;

public class RankingForm {

    @NotNull(message = "Codigo da Compra")
    private Long idCompra;
    @NotNull(message = "Codigo do vendedor")
    private Long idVendedor;


    public RankingForm(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public RankingForm() {

    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }


}
