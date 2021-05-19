package jus.trepe.br.sei.remote.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.HipoteseLegal;
import jus.trepe.br.sei.dto.Interessado;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.TipoProcesso;
import jus.trepe.br.sei.dto.processo.request.Processo;
import jus.trepe.br.sei.dto.processo.request.ProcessoCreate;
import jus.trepe.br.sei.dto.processo.request.ProcessoUpdate;
import jus.trepe.br.sei.dto.processo.response.ProcessoCreateResponse;
import jus.trepe.br.sei.dto.processo.response.ProcessoFindResponse;
import jus.trepe.br.sei.remote.exception.SeiException;

@DisplayName("Testes de processo")
public class ProcessoServiceTest extends SeiTest {
	
	private static final String NUMERO_PROTOCOLO_INVALIDO = "00000.000020/2021-00";
	private static final long ID_PROCESSO_INVALIDO = 10000L;
	
	@BeforeAll 
	public static void configService() {
		login();
	}
	
	@Test
	public void criarProcessoValido() {
		ProcessoCreateResponse processoResponse = criarProcessoPadrao();
		Assertions.assertNotNull(processoResponse.getId());
		Assertions.assertNotNull(processoResponse.getProtocolo());
		
		Processo processo = processoService.get(processoResponse.getId()).orElseThrow();		
		Assertions.assertEquals("JUnit - processo criado", processo.getDescricao());
		Assertions.assertEquals(TipoProcesso.TIPOS.get(0).getDescricao(), processo.getTipo());
	}
	
	@Test
	public void atualizarProcessoValido() {
		Long idProcesso = criarProcessoPadrao().getId();
		
		ProcessoUpdate update = new ProcessoUpdate();
		update.setId(idProcesso)
			  .addAssunto(Assunto.ASSUNTOS.get(1))
			  .addInteressado(new Interessado(ID_INTERESSADO_VALIDO))
			  .setHipoteseLegal(HipoteseLegal.HIPOTESES.get(2))
			  .setNivelAcesso(NivelAcesso.RESTRITO)
			  .setObservacao("Observações: processo atualizado - JUnit")
			  .setEspecificacao("JUnit - Processo atualizado")
			  .setTipo(TipoProcesso.TIPOS.get(1));
		processoService.update(update);
		
		Processo processo = processoService.get(idProcesso).orElseThrow();		
		Assertions.assertEquals(idProcesso, processo.getId());
		Assertions.assertEquals("JUnit - Processo atualizado", processo.getDescricao());
		Assertions.assertEquals(TipoProcesso.TIPOS.get(1).getDescricao(), processo.getTipo());
	}
	
	@Test
	public void pesquisarProcessoExistente() {
		ProcessoCreateResponse processoCreateResponse = criarProcessoPadrao();
		Processo processo = processoService.get(processoCreateResponse.getId()).orElseThrow();
		Assertions.assertEquals(processoCreateResponse.getId(), processo.getId());
		Assertions.assertNotNull(processo.getNumero());
	}
	
	@Test
	public void pesquisarProcessoIdInexistente() {
		Assertions.assertThrows(SeiException.class, () -> processoService.get(ID_PROCESSO_INVALIDO));
	}
	
	@Test
	public void pesquisarProcessoProtocoloValido() {
		ProcessoCreateResponse processoCreateResponse = criarProcessoPadrao();
		ProcessoFindResponse processo = processoService.get(processoCreateResponse.getProtocolo()).orElseThrow();
		Assertions.assertEquals(processoCreateResponse.getProtocolo(), processo.getProtocolo());
	}
	
	@Test
	public void pesquisarProcessoProtocoloInValido() {
		Assertions.assertThrows(SeiException.class, () -> processoService.get(NUMERO_PROTOCOLO_INVALIDO));
	}	
	
	public static ProcessoCreateResponse criarProcessoPadrao() {
		ProcessoCreate processoCreate = new ProcessoCreate();
		processoCreate.addAssunto(Assunto.ASSUNTOS.get(0))
		        .addInteressado(new Interessado(ID_INTERESSADO_VALIDO))
				.setEspecificacao("Especificação: processo de compra")
				.setHipoteseLegal(HipoteseLegal.HIPOTESES.get(0))
				.setNivelAcesso(NivelAcesso.RESTRITO)
				.setObservacao("Observações: processo criado - JUnit")	
				.setEspecificacao("JUnit - processo criado")
				.setTipo(TipoProcesso.TIPOS.get(0));
		
		return processoService.create(processoCreate).orElseThrow();
	}

}
