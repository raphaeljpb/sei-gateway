package jus.trepe.br.sei.dto.usuario.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jus.trepe.br.sei.dto.request.FormSubmission;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UsuarioLogin implements FormSubmission {
	
	@NonNull
	@JsonProperty("usuario")
	private String login;
	@NonNull
	private String senha;	

}
