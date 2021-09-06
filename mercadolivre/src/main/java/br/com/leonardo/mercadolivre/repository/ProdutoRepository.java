package br.com.leonardo.mercadolivre.repository;

import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}