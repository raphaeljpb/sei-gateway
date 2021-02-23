package jus.trepe.br.sei.remote.exception;

public class AcessoNegadoException extends SeiException {
	
	private static final long serialVersionUID = 4326189514722459047L;
	public static final String ACESSO_NEGADO_MESSAGE = "Acesso negado!";

	public AcessoNegadoException(String mensagem) {
		super(mensagem);
	}

}
