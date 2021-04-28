package jus.trepe.br.sei.dto.documento.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocumentoCreateResponse {
	
	@JsonProperty("idDocumento")
	private Long id;
	@JsonProperty("protocoloDocumentoFormatado")
	private String protocolo;

}
