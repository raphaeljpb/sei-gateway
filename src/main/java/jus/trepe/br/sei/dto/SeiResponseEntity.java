package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SeiResponseEntity<T> {
	
	private boolean sucesso;
	@JsonProperty(value = "data")
	private T entidade;
	private String mensagem;
	
	public boolean hasErrors() {
		return !sucesso;
	}
}
