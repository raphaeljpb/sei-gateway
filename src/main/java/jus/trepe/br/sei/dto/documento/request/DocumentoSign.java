package jus.trepe.br.sei.dto.documento.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jus.trepe.br.sei.dto.request.FormSubmission;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class DocumentoSign implements FormSubmission {
	
	@NonNull
	@JsonProperty("documento")
	private Long documentoId;
	@NonNull
	@JsonProperty("usuario")
	private Long idUsuario;
	@NonNull
	private String login;
	@NonNull
	private String senha;
	@NonNull
	private String cargo;	
	@NonNull
	@JsonProperty("orgao")
	private Integer idOrgao;
	

}
