package jus.trepe.br.sei.dto.response;

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
