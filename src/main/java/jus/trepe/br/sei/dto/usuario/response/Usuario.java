package jus.trepe.br.sei.dto.usuario.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import jus.trepe.br.sei.dto.Unidade;
import lombok.Data;

@Data
public class Usuario {
	
	private static final String ID_UNIDADE_ATUAL = "IdUnidadeAtual";
	@JsonProperty("token")
	private String tokenAutenticacao;
	private Map<String, ?> loginData;
	private Unidade unidade;
	
	@JsonSetter(value="loginData", nulls=Nulls.AS_EMPTY)
	private void configuraAtributos(Map<String, ?> atributos) {
		this.loginData = atributos;
		unidade = new Unidade(Long.parseLong((String) atributos.get(ID_UNIDADE_ATUAL)));
	}
	
}
