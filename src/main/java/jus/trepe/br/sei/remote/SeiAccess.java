package jus.trepe.br.sei.remote;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.Duration;

import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.error.SeiErrorHandler;
import jus.trepe.br.sei.remote.service.AuthenticationService;
import jus.trepe.br.sei.remote.service.SeiService;
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
	
	public RestTemplate buildTemplate(RestTemplateBuilder builder) {
		this.restTemplate = builder.setConnectTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.setReadTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.rootUri(baseUrl)
			.errorHandler(new SeiErrorHandler())
			.additionalInterceptors(new SeiAuthHeaderInterceptor(this.usuario))
			.additionalMessageConverters(new SeiJsonConverter())
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
			
			boolean authPath = request.getURI().getPath().endsWith("/autenticar");
			boolean autenticado = Strings.isNotEmpty(usuario.getTokenAutenticacao());
			
			if (!authPath) {
				if (!autenticado) {
					auth();
				}
				request.getHeaders().add(TOKEN_HEADER, this.usuario.getTokenAutenticacao());			
			} 
			
			ClientHttpResponse response = execution.execute(request, body);
			
			return response;
		}
		
		private void auth() {
			SeiService<Usuario> auth = new AuthenticationService(buildTemplate(new RestTemplateBuilder()));
			auth.post(this.usuario).ifPresent( u-> {
				this.usuario.setTokenAutenticacao(u.getTokenAutenticacao());
			});
				
		}
	}
	
	private class SeiJsonConverter extends MappingJackson2HttpMessageConverter {

		@Override
		public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
				throws IOException, HttpMessageNotReadableException {
			// TODO Auto-generated method stub
			System.out.println(new String(inputMessage.getBody().readAllBytes()));
			//modificar inputstream
			return super.read(type, contextClass, inputMessage);
		}

		
		
		
	}

}
