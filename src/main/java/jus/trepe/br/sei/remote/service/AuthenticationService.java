package jus.trepe.br.sei.remote.service;

import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.usuario.request.UsuarioLogin;
import jus.trepe.br.sei.dto.usuario.response.Usuario;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.SeiResponseEntity;

@SuppressWarnings("rawtypes")
public class AuthenticationService extends SeiService {
	
	private static final String AUTH_PATH = "/autenticar";
	
	public AuthenticationService(SeiAccess access) {
		super(access);
	}

	public AuthenticationService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@SuppressWarnings("unchecked")
	public Optional<Usuario> autenticar(UsuarioLogin usuario) {
		return this.post(AUTH_PATH, usuario, new ParameterizedTypeReference<SeiResponseEntity<Usuario>>(){});
	}

}
