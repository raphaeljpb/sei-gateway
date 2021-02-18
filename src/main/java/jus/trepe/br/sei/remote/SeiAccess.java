package jus.trepe.br.sei.remote;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Usuario;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SeiAccess {
	
	private static final int TIMEOUT_MINUTES = 1;
	private RestTemplate restTemplate;
	@NonNull
	private Usuario usuario;
	private final String baseUrl;
	@NonNull
	private static final String TOKEN_HEADER = "token";
	
	//TODO - adicionar autenticação->pode ser um request customizer or interceptor.
	//Então, haverá requests sem autenticação e com autenticação.
	public RestTemplate buildTemplate(RestTemplateBuilder builder) {
		log.debug("Usando token: "+usuario.getTokenAutenticacao());
		this.restTemplate = builder.setConnectTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.setReadTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.rootUri(baseUrl)
			.defaultHeader(TOKEN_HEADER, usuario.getTokenAutenticacao())
			.build();
		return this.restTemplate;
	}	
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

}
