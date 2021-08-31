package jus.trepe.br.sei.dto.usuario.response;

import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import jus.trepe.br.sei.dto.Unidade;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Usuario {
	
	private static final String ID_UNIDADE_ATUAL = "IdUnidadeAtual";
	@JsonProperty("token")
	private String tokenAutenticacao;
	private Map<String, ?> loginData;
	private Unidade unidade;
	private LocalDate dataLogin;
	
	public Usuario() {
		this.dataLogin = LocalDate.now();
	}
	
	@JsonSetter(value="loginData", nulls=Nulls.AS_EMPTY)
	private void configuraAtributos(Map<String, ?> atributos) {
		this.loginData = atributos;
		unidade = new Unidade(Long.parseLong((String) atributos.get(ID_UNIDADE_ATUAL)));
	}
	
}
