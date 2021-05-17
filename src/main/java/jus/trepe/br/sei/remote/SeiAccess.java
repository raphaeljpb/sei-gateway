package jus.trepe.br.sei.remote;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Unidade;
import jus.trepe.br.sei.dto.usuario.request.UsuarioLogin;
import jus.trepe.br.sei.dto.usuario.response.Usuario;
import jus.trepe.br.sei.remote.error.SeiErrorHandler;
import jus.trepe.br.sei.remote.service.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SeiAccess {
	
	private RestTemplate restTemplate;
	@NonNull
	private UsuarioLogin autenticacao;
	@NonNull
	private final String baseUrl;
	private Usuario usuario;
	private static final int TIMEOUT_MINUTES = 1;
	private static final String TOKEN_HEADER = "token";
	private static final String UNIDADE_HEADER = "unidade";
	private static final List<String> IGNORE_PATHS = List.of("/autenticar");
	
	public RestTemplate buildTemplate(RestTemplateBuilder builder) {
		this.restTemplate = builder.setConnectTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.setReadTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.rootUri(baseUrl)
			.errorHandler(new SeiErrorHandler())
			.additionalInterceptors(new SeiAuthHeaderInterceptor(autenticacao))
			.build();
		return this.restTemplate;
	}	
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}
	
	public void setAutenticacao(UsuarioLogin autenticacao) {
		this.autenticacao = autenticacao;
	}
	
	public void setUnidade(Unidade unidade) {
		this.usuario.setUnidade(unidade);
	}
	
	public void logout() {
		usuario = new Usuario();
	}	
	
	private class SeiAuthHeaderInterceptor implements ClientHttpRequestInterceptor {
		
		private UsuarioLogin autenticacao;
		
		public SeiAuthHeaderInterceptor(UsuarioLogin autenticacao) { 
			this.autenticacao = autenticacao;
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, 
				ClientHttpRequestExecution execution)
				throws IOException {
			
			boolean authPath = IGNORE_PATHS.stream().anyMatch((path) -> {
				return request.getURI().getPath().endsWith(path);
			});
			boolean autenticado = usuario != null && usuario.getTokenAutenticacao() != null;
			
			if (!authPath) {
				if (!autenticado) {
					auth();
				}
				request.getHeaders().add(TOKEN_HEADER, usuario.getTokenAutenticacao());
				if (usuario.getUnidade() != null) {
					request.getHeaders().addIfAbsent(UNIDADE_HEADER, usuario.getUnidade().getId().toString());
				}
			} 
			
			return execution.execute(request, body);
		}
		
		private void auth() {
			AuthenticationService auth = new AuthenticationService(buildTemplate(new RestTemplateBuilder()));
			usuario = auth.autenticar(this.autenticacao).orElseThrow();
		}
	}
}
