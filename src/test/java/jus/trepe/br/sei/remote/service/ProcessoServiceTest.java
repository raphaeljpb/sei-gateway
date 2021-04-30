package jus.trepe.br.sei.remote.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.HipoteseLegal;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.TipoProcesso;
import jus.trepe.br.sei.dto.processo.request.ProcessoCreate;
import jus.trepe.br.sei.dto.processo.response.ProcessoCreateResponse;

@DisplayName("Testes de processo")
public class ProcessoServiceTest extends SeiTest {
	
	static ProcessoService service;
	
	@BeforeAll 
	public static void configService() {
		service = new ProcessoService(sei);
		login();
	}
	
	@Test
	public void criarDocumentoValido() {
		ProcessoCreate processo = new ProcessoCreate();
		processo.addAssunto(Assunto.ASSUNTOS.get(0))
				.setEspecificacao("Especificação: processo de compra")
				.setHipoteseLegal(HipoteseLegal.HIPOTESES.get(0))
				.setNivelAcesso(NivelAcesso.RESTRITO)
				.setObservacoes("Observações: processo teste unitário")
				.setTipo(TipoProcesso.TIPOS.get(0));
		ProcessoCreateResponse processoResponse = service.create(processo).orElseThrow();
		Assertions.assertNotNull(processoResponse.getId());
		Assertions.assertNotNull(processoResponse.getProtocolo());
	}
	
	@Test
	public void atualizarDocumentoInvalido() {
		
	}
	
	@Test
	public void pesquisarDocumentoExistente() {
		
	}
	
	@Test
	public void pesquisarDocumentoInexistente() {
		
	}

}
