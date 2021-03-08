package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Processo;
import jus.trepe.br.sei.dto.request.ProcessoCreate;
import jus.trepe.br.sei.remote.SeiResponseEntity;

public class ProcessoService extends SeiService<Processo> {
	
	private static final String GET_PATH = "/processo/{protocolo}";
	private static final String CREATE_PATH = "/processo/criar";

	public ProcessoService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	public Optional<Processo> get(Long id) {
		return get(GET_PATH, new ParameterizedTypeReference<SeiResponseEntity<Processo>>(){}, Map.of("protocolo", id));
	}

	public Optional<?> criar(ProcessoCreate processo) {
		return post(CREATE_PATH, processo);
	}
	
}
