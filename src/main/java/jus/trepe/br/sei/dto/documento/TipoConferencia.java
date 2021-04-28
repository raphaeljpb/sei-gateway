package jus.trepe.br.sei.dto.documento;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
public class TipoConferencia {
	
	@JsonValue
	private int id;
	private String descricao;
	public static final List<TipoConferencia> TIPOS;
	
	static {
		TIPOS = Arrays.asList(
				new TipoConferencia(1, "Documento Original"),
				new TipoConferencia(2, "Cópia Autenticada por Cartório"),
				new TipoConferencia(3, "Cópia Autenticada Administrativamente"),
				new TipoConferencia(4, "Cópia Simples"));
	}

	public TipoConferencia(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}	
	
	

}
