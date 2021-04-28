package jus.trepe.br.sei.dto.processo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jus.trepe.br.sei.dto.Unidade;
import jus.trepe.br.sei.dto.processo.Informacoes;
import jus.trepe.br.sei.dto.processo.TipoProcedimento;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Processo {

	@JsonProperty("id_root")
	private Integer id;
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

