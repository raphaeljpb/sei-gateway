package jus.trepe.br.sei.dto.documento.response;

import lombok.Data;

@Data
public class InformacoesDocumento {

	private boolean bloqueado;
	private boolean sigiloso;
	private boolean restrito;
	private boolean publicado;
	private boolean assinado;
	private boolean ciencia;
	private boolean cancelado;
	private boolean podeVisualizar;
	private boolean permiteAssinatura;
	private boolean permiteAlteracao;
	
}
