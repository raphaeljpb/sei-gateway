package jus.trepe.br.sei.dto.documento.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocumentoListResponse {

	private Long id;
	@JsonProperty("atributos")
	private Map<String, ?> atributos;
	
}
