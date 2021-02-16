package jus.trepe.br.sei.remote;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SeiAccess {
	
	private static final int TIMEOUT_MINUTES = 1;
	private RestTemplate restTemplate;
	@NonNull
	private final String baseUrl;
	private String sessionToken="MDRhODk1NzY2MzliNjhmMWRjNDI1OWE1MTM1OGUyMTJkZjZlNjM5NWFUVnhUV2sxYnoxOGZHazFjVTFwTlc4OWZId3dmSHc9";
	private static final String TOKEN_HEADER = "token";

	public RestTemplate buildTemplate(RestTemplateBuilder builder) {
		this.restTemplate = builder.setConnectTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.setReadTimeout(Duration.ofMinutes(TIMEOUT_MINUTES))
			.rootUri(baseUrl)
			.defaultHeader(TOKEN_HEADER, sessionToken)
			.build();
		return this.restTemplate;
	}	
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

}
