package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Processo;
import jus.trepe.br.sei.dto.request.ProcessoCreate;
import jus.trepe.br.sei.remote.SeiResponseEntity;

public class ProcessoService extends SeiService<Processo> {

	public ProcessoService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public Map<HttpMethod, String> getPaths() {
		return Map.of(HttpMethod.GET,"/processo/{protocolo}", 
				HttpMethod.POST, "/processo/criar");
	}

	@Override
	public ParameterizedTypeReference<SeiResponseEntity<Processo>> getParameterizedTypeReference() {
		return new ParameterizedTypeReference<SeiResponseEntity<Processo>>(){};
	}
	
	@Override
	public Optional<Processo> get(Long id) {
		return super.get(id);
	}

	public Optional<?> criar(ProcessoCreate processo) {
		return post(getPaths().get(HttpMethod.POST), processo);
	}
	
}
