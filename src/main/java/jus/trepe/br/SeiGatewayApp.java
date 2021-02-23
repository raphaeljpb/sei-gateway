package jus.trepe.br;

import org.springframework.boot.web.client.RestTemplateBuilder;

import jus.trepe.br.sei.dto.Processo;
import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.service.AuthenticationService;
import jus.trepe.br.sei.remote.service.ProcessoService;
import jus.trepe.br.sei.remote.service.SeiService;

/**
 * Hello world!
 *
 */

public class SeiGatewayApp {

	public static void main(String[] args) {
		Usuario user = new Usuario("teste", "teste");
		SeiAccess sei = new SeiAccess(user, "http://localhost:8080/sei/modulos/wssei/controlador_ws.php/api/v2");
		SeiService<Usuario> auth = new AuthenticationService(sei.buildTemplate(new RestTemplateBuilder()));
		auth.post(user).ifPresent( u-> user.setTokenAutenticacao(u.getTokenAutenticacao()));
		
		SeiService<Processo> processo = new ProcessoService(sei.buildTemplate(new RestTemplateBuilder()));
		processo.get(1L).ifPresent(System.out::println);
		processo.get(2L).ifPresent(System.out::println);

	}
}
