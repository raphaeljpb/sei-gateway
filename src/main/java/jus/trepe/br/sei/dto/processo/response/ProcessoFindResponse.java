package jus.trepe.br.sei.dto.processo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProcessoFindResponse {
	
	@JsonProperty("IdProcedimento")
	private Integer id;
	@JsonProperty("ProtocoloProcedimentoFormatado")
	private String protocolo;
	@JsonProperty("NomeTipoProcedimento")
	private String nome;

}
