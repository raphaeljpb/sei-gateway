package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = Shape.OBJECT)
public enum TipoProcedimento {
	
	PROCEDIMENTO("P"),
	DOCUMENTO_GERADO("G"),
	DOCUMENTO_RECEBIDO("R");

	@JsonValue
	private String sigla;
	
	TipoProcedimento(String sigla) {
		this.sigla = sigla;
	}
	
	public String getSigla() {
		return this.sigla;
	}
	
}
