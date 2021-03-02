package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jus.trepe.br.sei.dto.processo.Informacoes;
import jus.trepe.br.sei.dto.processo.TipoProcedimento;
import lombok.Data;

@Data
public class Processo {

	@JsonProperty("id_root")
	private int id;
	@JsonProperty("status_root")
	private TipoProcedimento tipoProcedimento;
	private String numero;
	@JsonProperty("tipoProcesso")
	private String tipo;
	private String descricao;
	private Unidade unidade;
	@JsonProperty("status")
	private Informacoes informacoes;
}

