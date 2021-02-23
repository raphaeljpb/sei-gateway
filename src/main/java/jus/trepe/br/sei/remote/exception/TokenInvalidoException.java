package jus.trepe.br.sei.remote.exception;

public class TokenInvalidoException extends SeiException {
	
	private static final long serialVersionUID = 7383047449414673093L;
	public static final String TOKEN_INVALIDO_MESSAGE = "Token inválido!";

	public TokenInvalidoException(String mensagem) {
		super(mensagem);
	}

}
