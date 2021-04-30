package jus.trepe.br.sei.remote.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClientException;

import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.exception.SeiException;

@DisplayName("Testes de autenticação")
public class AuthenticationServiceTest extends SeiTest {
	
	@AfterEach
	public void logout() {
		sei.logout();
	}		
	
	@Test
	public void authWithWrongServer() {
		AuthenticationService authenticationService = 
				new AuthenticationService(new SeiAccess(validUser, "http://localhost:8181"));
		Assertions.assertThrows(RestClientException.class, () -> {
			authenticationService.autenticar(validUser);
		});
	}
	
	@Test
	public void authWithRightCredentials() {
		sei.setUsuario(validUser);
		AuthenticationService authenticationService = new AuthenticationService(sei);
		authenticationService.autenticar(validUser).ifPresent((usuario) -> {
			Assertions.assertNotNull(usuario.getTokenAutenticacao());
		});
	}
	
	@Test
	public void authWithoutUser() {
		sei.logout();
		AuthenticationService authenticationService = new AuthenticationService(sei);
		Assertions.assertThrows(SeiException.class, () -> {
			authenticationService.autenticar(new Usuario());
		});
	}	
	
	@Test
	public void authWithWrongCredentials() {
		sei.setUsuario(invalidUser);
		Assertions.assertThrows(SeiException.class, () -> {
			AuthenticationService authenticationService = new AuthenticationService(sei);
			authenticationService.autenticar(invalidUser);	
		});
	}

}
