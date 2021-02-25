package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Processo {

	private int id;
	@JsonProperty("status")
	private TipoProcedimento tipoProcedimento;
	private String numero;
	@JsonProperty("tipoProcesso")
	private String tipo;
	private String descricao;
	private Unidade unidade;
	private boolean sigiloso;
	private boolean restrito;
	private boolean ciencia;  
}

