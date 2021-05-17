package jus.trepe.br.sei.remote;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jus.trepe.br.sei.remote.exception.AcessoNegadoException;
import jus.trepe.br.sei.remote.exception.SeiException;
import jus.trepe.br.sei.remote.exception.TokenInvalidoException;
import jus.trepe.br.sei.remote.serialization.InnerWrapperObject;
import jus.trepe.br.sei.remote.serialization.InnerWrapperObjectDeserializer;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class SeiResponseEntity<T> {
	
	private boolean sucesso;
	@JsonProperty("data")
	@JsonDeserialize(using = InnerWrapperObjectDeserializer.class)
	@InnerWrapperObject("atributos")	
	private T entidade;
	private List<String> mensagens = new ArrayList<>();
	
	public void validate() {
		if (!sucesso) {
			if (mensagens.contains(TokenInvalidoException.TOKEN_INVALIDO_MESSAGE)) {
				throw new TokenInvalidoException(mensagens.toString());
			} else if (mensagens.contains(AcessoNegadoException.ACESSO_NEGADO_MESSAGE)) {
				throw new AcessoNegadoException(mensagens.toString());
			} else {
				throw new SeiException(mensagens.toString());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@JsonSetter(value="mensagem", nulls=Nulls.AS_EMPTY)
	public void configuraMensagem(Object mensagem) {
		if (mensagem instanceof List) {
			mensagens.addAll((List<String>) mensagem);   
		} else {
			mensagens.add(mensagem.toString());
		}
	}
}
