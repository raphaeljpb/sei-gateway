package jus.trepe.br.sei.dto.processo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = Shape.OBJECT)
public enum NivelAcesso {

	PUBLICO, RESTRITO, SIGILOSO;
	
	@JsonValue
	public int toValue() {
		return ordinal();
	}
	
	public boolean isPublico() {
		return this.ordinal() == PUBLICO.ordinal();
	}
	
	public boolean isRestrito() {
		return this.ordinal() == RESTRITO.ordinal();
	}
	
	public boolean isSigiloso() {
		return this.ordinal() == SIGILOSO.ordinal();
	}
	
	

}
