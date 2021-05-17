package jus.trepe.br;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.Unidade;
import jus.trepe.br.sei.dto.documento.TipoConferencia;
import jus.trepe.br.sei.dto.documento.TipoDocumento;
import jus.trepe.br.sei.dto.documento.request.DocumentoExternoCreate;
import jus.trepe.br.sei.dto.documento.request.DocumentoInternoCreate;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.TipoProcesso;
import jus.trepe.br.sei.dto.processo.request.ProcessoCreate;
import jus.trepe.br.sei.dto.processo.request.ProcessoUpdate;
import jus.trepe.br.sei.dto.processo.response.ProcessoCreateResponse;
import jus.trepe.br.sei.dto.usuario.request.UsuarioLogin;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.service.DocumentoService;
import jus.trepe.br.sei.remote.service.ProcessoService;

/**
 * Hello world!
 *
 */

public class SeiGatewayApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UsuarioLogin user = new UsuarioLogin("teste", "teste");
		SeiAccess sei = new SeiAccess(user, "http://localhost:8080/sei/modulos/wssei/controlador_ws.php/api/v2");
//		AuthenticationService auth = new AuthenticationService(sei.buildTemplate(new RestTemplateBuilder()));
//		auth.autenticar(user).ifPresent( u-> user.setTokenAutenticacao(u.getTokenAutenticacao()));
//		
		ProcessoService processoService = new ProcessoService(sei.buildTemplate(new RestTemplateBuilder()));
		processoService.get(1L).ifPresent(System.out::println);
//		processoService.get(2L).ifPresent(System.out::println);
		
		ProcessoCreate novo = new ProcessoCreate();
		novo.setTipo(TipoProcesso.TIPOS.get(0))
			.setNivelAcesso(NivelAcesso.PUBLICO)
			.addAssunto(Assunto.ASSUNTOS.get(2));
		
		Optional<ProcessoCreateResponse> response = processoService.create(novo);
		response.ifPresent((processo) -> {
			processoService.get(processo.getProtocolo()).ifPresent(System.out::println);
			
			ProcessoUpdate update = new ProcessoUpdate();
			update.setId(processo.getId())
								 .setTipo(TipoProcesso.TIPOS.get(1))
								 .setNivelAcesso(NivelAcesso.PUBLICO)
								 .setObservacao("Processo Alterado")
								 .addAssunto(Assunto.ASSUNTOS.get(0));
			processoService.update(update);			
			processoService.get(processo.getId()).ifPresent(System.out::println);
			
			DocumentoService documentoService = new DocumentoService(sei.buildTemplate(new RestTemplateBuilder()));
			DocumentoInternoCreate documento = new DocumentoInternoCreate();
			documento.addAssunto(Assunto.ASSUNTOS.get(0))
					 .setTipoDocumento(TipoDocumento.TIPOS.get(1))
					 .setNivelAcesso(NivelAcesso.PUBLICO)
					 .setObservacao("Documento com observação")
					 .setUnidadeGeradora(new Unidade(110000004L));			
			 
			documentoService.create(processo.getId(), documento);
			
			DocumentoExternoCreate documentoExterno = new DocumentoExternoCreate();
			documentoExterno.setDataElaboracao(LocalDate.now())
					.setAnexo(new FileSystemResource("C:\\Users\\rapha\\OneDrive\\Documents\\unimed_boleto_abril.pdf"))
					.setTipoConferencia(TipoConferencia.TIPOS.get(0))
					.setTipoDocumento(TipoDocumento.TIPOS.get(1))
					.setNivelAcesso(NivelAcesso.PUBLICO)
					.setObservacao("Documento com observação")
					.setUnidadeGeradora(new Unidade(110000004L))
					.addAssunto(Assunto.ASSUNTOS.get(0));
			documentoService.create(processo.getId(), documentoExterno).ifPresent((documentoResponse) -> {
				documentoService.get(documentoResponse.getId(), jus.trepe.br.sei.remote.service.TipoDocumento.INTERNO);
				documentoService.download(documentoResponse.getId());
			});
		});

	}
}
