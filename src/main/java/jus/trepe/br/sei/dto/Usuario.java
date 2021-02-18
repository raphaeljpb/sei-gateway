package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Usuario {
	
	@NonNull
	@JsonProperty(value="usuario")
	private String login;
	@NonNull
	private String senha;
	private String nome;
	@JsonProperty(value="token")
	private String tokenAutenticacao;
	
}
