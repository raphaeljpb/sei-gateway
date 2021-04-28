package jus.trepe.br.sei.dto.processo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProcessoCreateResponse {
	
	@JsonProperty("IdProcedimento")
	private Long id;
	@JsonProperty("ProtocoloFormatado")
	private String protocolo;

}
