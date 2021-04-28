package jus.trepe.br.sei.remote.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClientException;

import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.exception.SeiException;

public class AuthenticationTest {
	
	static SeiAccess sei;
	static String SEI_URL = "http://localhost:8080/sei/modulos/wssei/controlador_ws.php/api/v2";
	static Usuario validUser;
	static Usuario invalidUser;
	
	@BeforeAll
	public static void config() {
		validUser = new Usuario("teste", "teste");
		invalidUser = new Usuario("teste", "test");
		sei = new SeiAccess(new Usuario(), SEI_URL);		
	}
	
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
