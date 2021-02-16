package jus.trepe.br;

import org.springframework.boot.web.client.RestTemplateBuilder;

import jus.trepe.br.sei.dto.Processo;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.service.ProcessoService;
import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */

@Slf4j
public class SeiGatewayApp {
	
	public static void main(String[] args) {
		SeiAccess sei = new SeiAccess("http://localhost:8080/sei/modulos/wssei/controlador_ws.php/api/v2");		
		ProcessoService processoService = new ProcessoService(sei.buildTemplate(new RestTemplateBuilder()));
		processoService.getElement(1L).ifPresent(System.out::println);

		
//		String response = sei.getRestTemplate().getForObject("http://localhost:8080/sei/", String.class);
//		System.out.println(response);
	}
}
