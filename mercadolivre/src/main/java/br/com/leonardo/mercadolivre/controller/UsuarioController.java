package br.com.leonardo.mercadolivre.controller;


import br.com.leonardo.mercadolivre.dto.usuario.UsuarioForm;
import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public void Salvar(@RequestBody @Valid UsuarioForm usuario){
    Usuario user = usuario.toModel();
    usuarioRepository.save(user);
    }
}
