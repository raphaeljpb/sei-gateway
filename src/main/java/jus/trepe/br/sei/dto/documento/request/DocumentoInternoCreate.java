package jus.trepe.br.sei.dto.documento.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentoInternoCreate extends DocumentoCreate {
	
	private String descricao;

}
