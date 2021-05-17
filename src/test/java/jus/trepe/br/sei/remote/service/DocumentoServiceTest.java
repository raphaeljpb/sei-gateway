package jus.trepe.br.sei.remote.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.HipoteseLegal;
import jus.trepe.br.sei.dto.Interessado;
import jus.trepe.br.sei.dto.Unidade;
import jus.trepe.br.sei.dto.documento.TipoConferencia;
import jus.trepe.br.sei.dto.documento.TipoDocumento;
import jus.trepe.br.sei.dto.documento.request.DocumentoExternoCreate;
import jus.trepe.br.sei.dto.documento.request.DocumentoInternoCreate;
import jus.trepe.br.sei.dto.documento.request.DocumentoSign;
import jus.trepe.br.sei.dto.documento.response.Documento;
import jus.trepe.br.sei.dto.documento.response.DocumentoCreateResponse;
import jus.trepe.br.sei.dto.documento.response.DocumentoListResponse;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.response.ProcessoCreateResponse;

@DisplayName("Testes de documentos")
@TestMethodOrder(OrderAnnotation.class)
public class DocumentoServiceTest extends SeiTest {
	
	private static final String CARGO_TESTE = "Presidente";
	static DocumentoService service;
	static ProcessoCreateResponse createResponse;
	static Long processoId;
	static Long ID_USUARIO_TESTE = 100000001L;
	
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
		Assertions.assertFalse(service.viewContent(documento.getId()).orElseThrow().isEmpty());
	}
	
	@Test
	@Order(2)
	public void criaDocumentoExternoValido() throws IOException {
		DocumentoExternoCreate documentoExternoCreate = new DocumentoExternoCreate();
		LocalDate now = LocalDate.now();
		Resource resource = new FileSystemResource("blank.pdf");
		documentoExternoCreate.setDataElaboracao(now)
				.setAnexo(resource)
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
		Assertions.assertFalse(documento.getNome().isEmpty());
		Assertions.assertEquals(now, documento.getDataElaboracao());
		Assertions.assertEquals(resource.contentLength(), service.download(documento.getId()).length);
	}
	
	@Test
	@Order(3)
	public void listaDocumentosProcesso() {		
		List<DocumentoListResponse> response = service.list(processoId).orElseThrow();		
		Assertions.assertEquals(2, response.size());
	}
	
	@Test
	@Order(4)
	public void assinaDocumento() {
		service.list(processoId).ifPresent((documentos)->{
			documentos.forEach((documento) -> {
				DocumentoSign sign = DocumentoSign.builder()
							.idOrgao(1)
							.idUsuario(ID_USUARIO_TESTE)
							.login("teste")
							.senha("teste")
							.cargo(CARGO_TESTE)
							.documentoId(documento.getId())
							.build();
				service.sign(sign);
			});
		});
	}
	
}
