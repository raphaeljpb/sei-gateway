package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.SeiResponseEntity;

public class AuthenticationService extends SeiService<Usuario> {

	public AuthenticationService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public Map<HttpMethod, String> getPaths() {
		return Map.of(HttpMethod.POST,"/autenticar");
	}

	@Override
	public ParameterizedTypeReference<SeiResponseEntity<Usuario>> getParameterizedTypeReference() {
		return new ParameterizedTypeReference<SeiResponseEntity<Usuario>>(){};
	}
	
	public Optional<Usuario> autenticar(Usuario usuario) {
		return this.post(usuario);
	}

}
