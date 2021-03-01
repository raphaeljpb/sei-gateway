package jus.trepe.br.sei.remote.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Processo;
import jus.trepe.br.sei.remote.SeiResponseEntity;

public class ProcessoService extends SeiService<Processo> {

	public ProcessoService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public Map<HttpMethod, String> getPaths() {
		return Map.of(HttpMethod.GET,"/processo/{protocolo}");
	}

	@Override
	public ParameterizedTypeReference<SeiResponseEntity<Processo>> getParameterizedTypeReference() {
		return new ParameterizedTypeReference<SeiResponseEntity<Processo>>(){};
	}
	
}
