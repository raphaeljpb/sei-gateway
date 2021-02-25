package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Unidade {
	
	@JsonProperty("idUnidade")
	private String id;
	private String sigla;

}
