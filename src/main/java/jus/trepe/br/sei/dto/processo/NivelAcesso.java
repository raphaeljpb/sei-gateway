package jus.trepe.br.sei.dto.processo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum NivelAcesso {

	PUBLICO, RESTRITO, SIGILOSO;

}
