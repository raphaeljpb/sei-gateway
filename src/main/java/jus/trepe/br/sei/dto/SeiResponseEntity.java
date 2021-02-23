package jus.trepe.br.sei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jus.trepe.br.sei.remote.exception.AcessoNegadoException;
import jus.trepe.br.sei.remote.exception.SeiException;
import jus.trepe.br.sei.remote.exception.TokenInvalidoException;
import lombok.Data;

@Data
public class SeiResponseEntity<T> {
	
	private boolean sucesso;
	@JsonProperty(value = "data")
	private T entidade;
	private String mensagem;
	
	public void validate() {
		if (!sucesso) {
			if (this.mensagem.equalsIgnoreCase(TokenInvalidoException.TOKEN_INVALIDO_MESSAGE)) {
				throw new TokenInvalidoException(this.mensagem);
			} else if (this.mensagem.equalsIgnoreCase(AcessoNegadoException.ACESSO_NEGADO_MESSAGE)) {
				throw new AcessoNegadoException(this.mensagem);
			} else {
				throw new SeiException(this.mensagem);
			}
		}
	}
}
