package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Processo;

public class ProcessoService extends SeiService<Processo> {

	public ProcessoService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public String getPath() {
		return "/processo/{protocolo}";
	}

	@Override
	public Optional<Processo> get(Long id) {
		return Optional.ofNullable(getRestTemplate().getForObject(getPath(), Processo.class, Map.of("protocolo", id)));
	}
	
}
