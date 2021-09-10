package br.com.leonardo.mercadolivre.dto.retornogateway;

import br.com.leonardo.mercadolivre.model.enuns.StatusTransacao;

public enum StatusRetornoPagSeguro {
    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.SUCESSO;
        }

        return StatusTransacao.ERRO;
    }
}
