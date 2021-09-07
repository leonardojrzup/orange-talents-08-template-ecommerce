package br.com.leonardo.mercadolivre.dto.FotosProdutos;

import br.com.leonardo.mercadolivre.model.FotosProdutos;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class FotosProdutosForm {

    @Size(min = 1)
    @NotNull(message = "Links")
    private List<MultipartFile> links = new ArrayList<>();

    public FotosProdutosForm(List<MultipartFile> links) {
        this.links = links;
    }

    public List<MultipartFile> getLinks() {
        return links;
    }

public List<FotosProdutos> toModel(){
    List<FotosProdutos> imagens = new ArrayList<>();
        this.links.forEach(imagem -> {
        imagens.add(new FotosProdutos(imagem.getOriginalFilename()));
    });
        return imagens;
}

    public void setLinks(List<MultipartFile> links) {
        this.links = links;
    }
}
