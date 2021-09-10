package apiexterna;


import apiexterna.dto.NfeForm;
import apiexterna.dto.RankingForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

//Feing que simula uma API externa


@FeignClient(name = "sistema-externo", url = "${feign.client.api-externa}")
//@FeignClient(name = "sistema-externo", url = "localhost:8080/api-externa") - não fuciona
public interface ApiExterna {

    @PostMapping(value = "/rankings")
    String comunicaSistemaRanking(@RequestBody @Valid RankingForm request);

    @PostMapping(value = "/notas-fiscais")
    String comunicaSistemaNotaFiscal(@RequestBody @Valid NfeForm request);

}