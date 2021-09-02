package br.com.leonardo.mercadolivre.annotations;

import br.com.leonardo.mercadolivre.model.Usuario;
import br.com.leonardo.mercadolivre.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniqueValidatorTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    Validator validator;

    static class ClienteTest {


        @Unique(fieldName = "login", domainClass = Usuario.class)
        private String login;

        public ClienteTest(String login) {
            this.login = login;
        }
    }


    @Test
    public void validarSeExiste() {
        usuarioRepository.save(new Usuario("leonardo.junior@zup.com.br", "123456"));
        Set<ConstraintViolation<ClienteTest>> validate = validator.validate(new ClienteTest("leonardo.junior@zup.com.br"));
        Assertions.assertFalse(validate.isEmpty());//validar se falso, como a lista não é vazia, retorna falso e o teste passa!
    }

    @Test
    public void validarSeNaoExiste() {
      //  usuarioRepository.save(new Usuario("leonardo@zup.com.br", "123456")); não necessario incluir no banco ja que é pra verificar se não existe
        Set<ConstraintViolation<ClienteTest>> validate = validator.validate(new ClienteTest("leonardo@zup.com.br"));
        Assertions.assertTrue(validate.isEmpty());//validar se falso, como a lista não é vazia, retorna falso e o teste passa!
    }


}
