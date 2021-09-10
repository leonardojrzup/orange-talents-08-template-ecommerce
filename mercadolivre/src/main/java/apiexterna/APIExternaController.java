package apiexterna;

import apiexterna.dto.NfeForm;
import apiexterna.dto.RankingForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//implementa a logica do Feing

@RestController
@RequestMapping("/api-externa")
public class APIExternaController {

    @PostMapping("/rankings")
    public String conectarSistemaRanking(@RequestBody RankingForm request) {
        System.out.println("Ranking atualizado, a posição do vendedor " + request.getIdVendedor() + " é 5ª lugar");
        return request.toString();
    }

    @PostMapping("/notas-fiscais")
    public String conectarSistemaNotas(@RequestBody NfeForm request) {
        System.out.println("Emissão da Notas Fiscal realizada com sucesso!!!");
        return request.toString();
    }

}


