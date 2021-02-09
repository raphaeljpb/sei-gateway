package jus.trepe.br.remote;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestAccess {
	
	private static final int TIMEOUT_MINUTES = 1;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.setReadTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.build();
	}	

}
