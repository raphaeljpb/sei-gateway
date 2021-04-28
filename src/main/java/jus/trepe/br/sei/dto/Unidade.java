package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Unidade {
	
	@JsonProperty("idUnidade")
	@JsonValue
	private Long id;
	private String sigla;
	
	public Unidade(Long id) {
		this.id = id;
	}

}
