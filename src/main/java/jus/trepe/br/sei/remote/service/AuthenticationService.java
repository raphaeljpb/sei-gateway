package jus.trepe.br.sei.remote.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.SeiResponseEntity;
import jus.trepe.br.sei.dto.Usuario;

public class AuthenticationService extends SeiService<Usuario> {

	public AuthenticationService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public String getPath() {
		return "/autenticar";
	}

	@Override
	public ParameterizedTypeReference<SeiResponseEntity<Usuario>> getParameterizedTypeReference() {
		return new ParameterizedTypeReference<SeiResponseEntity<Usuario>>(){};
	}

}
