package br.com.leonardo.mercadolivre.controller;


import antlr.Token;
import br.com.leonardo.mercadolivre.config.security.TokenService;
import br.com.leonardo.mercadolivre.dto.token.TokenDto;
import br.com.leonardo.mercadolivre.dto.usuario.UsuarioForm;
import br.com.leonardo.mercadolivre.dto.usuario.UsuarioLogin;
import br.com.leonardo.mercadolivre.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.Authenticator;
import java.util.Date;

@RestController
@RequestMapping("/auth")

public class AutenticacaoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    public AuthenticationManager authenticationManager;
    private UsernamePasswordAuthenticationToken dadosLogin;


    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid UsuarioLogin form) { // Criada uma nova classe de login para validarmos se os dados passados no corpo da requisição são válidos, dessa forma nessa classe não há a validação de se é Unico ou não
        UsernamePasswordAuthenticationToken dadosLogin = form.toAuthenticationToken();
        // UsernamePasswordAuthenticationToken dadosLogin = form.toModel().toAuthenticationToken();//Pra retornar a senha ja encriptada
        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

