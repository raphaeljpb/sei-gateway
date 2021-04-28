package jus.trepe.br.sei.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jus.trepe.br.sei.dto.request.FormSubmission;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Usuario implements FormSubmission {
	
	@NonNull
	@JsonProperty("usuario")
	private String login;
	@NonNull
	private String senha;
	private String nome;
	@JsonProperty("token")
	private String tokenAutenticacao;
	@JsonProperty("loginData")
	private Map<String, ?> loginData;
	private Unidade unidade;
	
}
