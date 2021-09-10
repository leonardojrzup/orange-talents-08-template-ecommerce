package br.com.leonardo.mercadolivre.repository;

import br.com.leonardo.mercadolivre.model.Transacao;
import org.springframework.data.repository.CrudRepository;

public interface TransacaoRepository extends CrudRepository <Transacao, Long> {


}
