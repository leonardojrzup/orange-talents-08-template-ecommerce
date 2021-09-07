package br.com.leonardo.mercadolivre.dto.FotosProdutos;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FotosProdutosForm {

    @NotNull(message = "Link")
    private List<String> links = new ArrayList<>();

    public FotosProdutosForm(List<String> links) {
        this.links = links;
    }

    public List<String> getLinks() {
        return links;
    }
}
