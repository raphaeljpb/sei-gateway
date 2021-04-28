package jus.trepe.br.sei.remote.service;

import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.SeiResponseEntity;

public class AuthenticationService extends SeiService<Usuario> {
	
	private static final String AUTH_PATH = "/autenticar";
	
	public AuthenticationService(SeiAccess access) {
		super(access);
	}

	public AuthenticationService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	public Optional<Usuario> autenticar(Usuario usuario) {
		return this.post(AUTH_PATH, usuario, new ParameterizedTypeReference<SeiResponseEntity<Usuario>>(){});
	}

}
