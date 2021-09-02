package br.com.leonardo.mercadolivre.repository;

import br.com.leonardo.mercadolivre.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String email);

     Optional<Usuario> findById(Long id);

}
