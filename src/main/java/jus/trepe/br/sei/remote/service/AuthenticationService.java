package jus.trepe.br.sei.remote.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.SeiResponseEntity;
import jus.trepe.br.sei.dto.Usuario;

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

}
