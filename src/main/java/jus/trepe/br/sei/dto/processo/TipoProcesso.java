package jus.trepe.br.sei.dto.processo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
public class TipoProcesso {
	
	public static final List<TipoProcesso> TIPOS;
	@JsonValue
	private int id;
	private String descricao;
	
	static {
		TIPOS = Arrays.asList(new TipoProcesso(100000381, "Acesso à Informação: Demanda do e-SIC"),
				new TipoProcesso(100000256, "Arrecadação: Cobrança"));
	}
	
	public TipoProcesso(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	

}
