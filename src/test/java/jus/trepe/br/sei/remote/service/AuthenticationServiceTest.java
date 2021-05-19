package jus.trepe.br.sei.remote.service;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClientException;

import jus.trepe.br.sei.dto.usuario.request.UsuarioLogin;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.exception.SeiException;
import jus.trepe.br.sei.remote.exception.TokenInvalidoException;

@DisplayName("Testes de autenticação")
public class AuthenticationServiceTest extends SeiTest {
	
	private static final String INVALID_TOKEN = "xyz";

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
		sei.setAutenticacao(validUser);
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
			authenticationService.autenticar(new UsuarioLogin());
		});
	}	
	
	@Test
	public void authWithWrongCredentials() {
		sei.setAutenticacao(invalidUser);
		Assertions.assertThrows(SeiException.class, () -> {
			AuthenticationService authenticationService = new AuthenticationService(sei);
			authenticationService.autenticar(invalidUser);	
		});
	}
	
	@Test
	public void acessoTokenInvalido() {
		sei.logout();
		ProcessoService service = new ProcessoService(sei);
		sei.getRestTemplate().getInterceptors().add(new ClientHttpRequestInterceptor() {

			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().replace("token", List.of(INVALID_TOKEN));
				
				return execution.execute(request, body);
			}
			
		});
		Assertions.assertThrows(TokenInvalidoException.class, () -> service.get(1L));
		config();
	}

}
