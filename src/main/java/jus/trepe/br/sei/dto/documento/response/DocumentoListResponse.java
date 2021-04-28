package jus.trepe.br.sei.dto.documento.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocumentoListResponse {

	private Integer id;
	private Integer idProcedimento;
	private Integer idProtocolo;
	private String protocoloFormatado;
	private String nome;
	private String titulo;
	private String tipo;
	private String mimeType;
	private String nomeComposto;
	@JsonProperty("status")
	private InformacoesDocumento informacoes;
	
	
}
