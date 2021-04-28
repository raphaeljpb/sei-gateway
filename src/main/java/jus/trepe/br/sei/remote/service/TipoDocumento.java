package jus.trepe.br.sei.remote.service;

public enum TipoDocumento {
	
	INTERNO("interno"),
	EXTERNO("externo");
	
	private String path;
	
	TipoDocumento(String path) {
		this.path = path;
	}

	String getPath() {
		return this.path;
	}

}
