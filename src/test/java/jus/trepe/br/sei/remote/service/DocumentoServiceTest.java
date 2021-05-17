package jus.trepe.br.sei.remote.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.HipoteseLegal;
import jus.trepe.br.sei.dto.Interessado;
import jus.trepe.br.sei.dto.Unidade;
import jus.trepe.br.sei.dto.documento.TipoConferencia;
import jus.trepe.br.sei.dto.documento.TipoDocumento;
import jus.trepe.br.sei.dto.documento.request.DocumentoExternoCreate;
import jus.trepe.br.sei.dto.documento.request.DocumentoInternoCreate;
import jus.trepe.br.sei.dto.documento.response.Documento;
import jus.trepe.br.sei.dto.documento.response.DocumentoCreateResponse;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.response.ProcessoCreateResponse;

public class DocumentoServiceTest extends SeiTest {
	
	static DocumentoService service;
	static ProcessoCreateResponse createResponse;
	static Long processoId;
	
	@BeforeAll 
	public static void configService() {
		service = new DocumentoService(sei);
		login();
		createResponse = ProcessoServiceTest.criarProcessoPadrao();
		processoId = createResponse.getId();
	}	
	
	@Test
	@Order(1)
	public void criaDocumentoInternoValido() {
		DocumentoInternoCreate documentoInternoCreate = new DocumentoInternoCreate();
		documentoInternoCreate.setDescricao("JUnit - Documento Interno")
							 .setHipoteseLegal(HipoteseLegal.HIPOTESES.get(0))
							 .setNivelAcesso(NivelAcesso.RESTRITO)
							 .setTipoDocumento(TipoDocumento.TIPOS.get(0))
							 .setObservacao("JUnit - Observação documento interno")
							 .setUnidadeGeradora(new Unidade(ID_INTERESSADO_VALIDO))
							 .addAssunto(Assunto.ASSUNTOS.get(0))
							 .addInteressado(new Interessado(ID_INTERESSADO_VALIDO));
		
		DocumentoCreateResponse documentoCreateResponse =  service.create(processoId, documentoInternoCreate).orElseThrow();
		
		Documento documento = service.get(documentoCreateResponse.getId(), jus.trepe.br.sei.remote.service.TipoDocumento.INTERNO).orElseThrow();
		Assertions.assertEquals(documentoCreateResponse.getId(), documento.getId());
	}
	
	@Test
	@Order(2)
	public void criaDocumentoExternoValido() {
		DocumentoExternoCreate documentoExternoCreate = new DocumentoExternoCreate();
		LocalDate now = LocalDate.now();
		documentoExternoCreate.setDataElaboracao(now)
				.setAnexo(null)
				.setTipoConferencia(TipoConferencia.TIPOS.get(0))
				.setHipoteseLegal(HipoteseLegal.HIPOTESES.get(0))
				.setNivelAcesso(NivelAcesso.RESTRITO)
				.setTipoDocumento(TipoDocumento.TIPOS.get(0))
				.setObservacao("JUnit - Observação documento externo")
				.setUnidadeGeradora(new Unidade(ID_INTERESSADO_VALIDO))
				.addAssunto(Assunto.ASSUNTOS.get(0))
				.addInteressado(new Interessado(ID_INTERESSADO_VALIDO));
		
		DocumentoCreateResponse documentoCreateResponse = service.create(processoId, documentoExternoCreate).orElseThrow();		
		
		Documento documento = service.get(documentoCreateResponse.getId(), jus.trepe.br.sei.remote.service.TipoDocumento.EXTERNO).orElseThrow();
		Assertions.assertEquals(documentoCreateResponse.getId(), documento.getId());
		Assertions.assertEquals("", documento.getNome());
		Assertions.assertEquals(now, documento.getDataElaboracao());
//		service.download(processoId);
	}
	
	@Test
	@Order(3)
	public void listaDocumentosProcesso() {		
		Assertions.assertEquals(2, service.list(processoId).orElseThrow().size());
	}

}
