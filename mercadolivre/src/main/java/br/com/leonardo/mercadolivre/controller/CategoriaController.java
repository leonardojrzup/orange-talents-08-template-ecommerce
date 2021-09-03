package br.com.leonardo.mercadolivre.controller;


import br.com.leonardo.mercadolivre.dto.categoria.CategoriaForm;
import br.com.leonardo.mercadolivre.model.Categoria;
import br.com.leonardo.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;


    @PostMapping
    public void Salvar(@RequestBody @Valid CategoriaForm categoriaForm) {
    Categoria categoria = categoriaForm.toModel(categoriaRepository);
    categoriaRepository.save(categoria);

    }
}

