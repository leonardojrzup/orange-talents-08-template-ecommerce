package br.com.leonardo.mercadolivre.controller;


import br.com.leonardo.mercadolivre.dto.caracteristica.CaracteristicaForm;
import br.com.leonardo.mercadolivre.model.Caracteristica;
import br.com.leonardo.mercadolivre.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;



    @PostMapping
    public void Salvar(@RequestBody @Valid CaracteristicaForm form) {
       // Caracteristica crctrc = form.toModel();
      //  caracteristicaRepository.save(crctrc);

    }

}
