package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Processo;
import jus.trepe.br.sei.dto.request.ProcessoCreate;
import jus.trepe.br.sei.dto.request.ProcessoUpdate;
import jus.trepe.br.sei.dto.response.ProcessoCreateResponse;
import jus.trepe.br.sei.remote.SeiResponseEntity;

@SuppressWarnings("rawtypes")
public class ProcessoService extends SeiService {
	
	private static final String GET_PATH = "/processo/{protocolo}";
	private static final String CREATE_PATH = "/processo/criar";
	private static final String UPDATE_PATH = "/processo/{protocolo}/alterar";
	private static final String SEARCH_PATH = "/processo/pesquisar";

	public ProcessoService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@SuppressWarnings("unchecked")
	public Optional<Processo> get(Long id) {
		return get(GET_PATH, new ParameterizedTypeReference<SeiResponseEntity<Processo>>(){}, Map.of("protocolo", id));
	}
	
	@SuppressWarnings("unchecked")
	public Optional<?> get(String protocolo) {
		return get(SEARCH_PATH, Map.of("buscaRapida", protocolo), new ParameterizedTypeReference<SeiResponseEntity<Processo>>(){});
	}	

	@SuppressWarnings("unchecked")
	public Optional<ProcessoCreateResponse> create(ProcessoCreate processo) {
		return post(CREATE_PATH, processo, new ParameterizedTypeReference<SeiResponseEntity<ProcessoCreateResponse>>(){});
	}
	
	@SuppressWarnings("unchecked")
	public Optional<?> update(ProcessoUpdate processo) {
		return post(UPDATE_PATH, processo, Map.of("protocolo", processo.getId()));
	}	
	
	public void addDocumento() {
		
	}
	
}
