package jus.trepe.br.sei.dto.documento.request;

import jus.trepe.br.sei.dto.request.FormSubmission;
import lombok.Data;
import lombok.NonNull;

@Data
public class DocumentoSign implements FormSubmission {
	
	@NonNull
	private Long id;
	@NonNull
	private String login;
	@NonNull
	private String senha;
	

}
