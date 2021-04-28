package jus.trepe.br.sei.dto.documento.request;

import jus.trepe.br.sei.dto.request.FormSubmission;
import lombok.Data;

@Data
public class DocumentoSign implements FormSubmission {
	
	private Long id;
	private String login;
	private String senha;
	

}
