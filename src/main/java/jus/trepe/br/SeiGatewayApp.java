package jus.trepe.br;

import org.springframework.boot.web.client.RestTemplateBuilder;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.TipoProcesso;
import jus.trepe.br.sei.dto.request.ProcessoCreate;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.service.ProcessoService;

/**
 * Hello world!
 *
 */

public class SeiGatewayApp {

	public static void main(String[] args) {
		Usuario user = new Usuario("teste", "teste");
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
		
		Object o = processoService.criar(novo);
		System.out.println(o);

	}
}
