package jus.trepe.br.sei.dto.documento;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoDocumento {
	
	public static final List<TipoDocumento> TIPOS;
	@JsonValue
	private int id;
	private String descricao;
	
	static {
		TIPOS = Arrays.asList(new TipoDocumento(1, "Resolução"), new TipoDocumento(10, "Portaria"));
	}

}
