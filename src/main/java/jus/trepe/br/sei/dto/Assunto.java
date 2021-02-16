package jus.trepe.br.sei.dto;

import lombok.Data;

@Data
public class Assunto {

    String codigoEstruturado;
    String descricao;

    public Assunto(String codigoEstruturado, String descricao) {
        this.codigoEstruturado = codigoEstruturado;
        this.descricao = descricao;
    }

}
