package jus.trepe.br.sei.remote;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.error.SeiErrorHandler;
import jus.trepe.br.sei.remote.service.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SeiAccess {
	
	private static final int TIMEOUT_MINUTES = 1;
	private RestTemplate restTemplate;
	@NonNull
	private Usuario usuario;
	private final String baseUrl;
	@NonNull
	private static final String TOKEN_HEADER = "token";
	private static final List<String> IGNORE_PATHS = List.of("/autenticar");
	
	public RestTemplate buildTemplate(RestTemplateBuilder builder) {
		this.restTemplate = builder.setConnectTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.setReadTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.rootUri(baseUrl)
			.errorHandler(new SeiErrorHandler())
			.additionalInterceptors(new SeiAuthHeaderInterceptor(this.usuario))
			.build();
		return this.restTemplate;
	}	
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}
	
	private class SeiAuthHeaderInterceptor implements ClientHttpRequestInterceptor {
		
		private Usuario usuario;
		
		public SeiAuthHeaderInterceptor(Usuario usuario) { 
			this.usuario = usuario;
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, 
				ClientHttpRequestExecution execution)
				throws IOException {
			
			boolean authPath = IGNORE_PATHS.stream().anyMatch((path) -> {
				return request.getURI().getPath().endsWith(path);
			});
			boolean autenticado = Strings.isNotEmpty(usuario.getTokenAutenticacao());
			
			if (!authPath) {
				if (!autenticado) {
					auth();
				}
				request.getHeaders().add(TOKEN_HEADER, this.usuario.getTokenAutenticacao());			
			} 
			
			return execution.execute(request, body);
		}
		
		private void auth() {
			AuthenticationService auth = new AuthenticationService(buildTemplate(new RestTemplateBuilder()));
			auth.autenticar(this.usuario).ifPresent( u-> {
				this.usuario.setTokenAutenticacao(u.getTokenAutenticacao());
			});
				
		}
	}

}
