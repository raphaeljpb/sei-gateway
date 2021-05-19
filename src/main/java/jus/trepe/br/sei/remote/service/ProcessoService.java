package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.processo.request.Processo;
import jus.trepe.br.sei.dto.processo.request.ProcessoCreate;
import jus.trepe.br.sei.dto.processo.request.ProcessoUpdate;
import jus.trepe.br.sei.dto.processo.response.ProcessoCreateResponse;
import jus.trepe.br.sei.dto.processo.response.ProcessoFindResponse;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.SeiResponseEntity;

@SuppressWarnings("rawtypes")
public class ProcessoService extends SeiService {
	
	private static final String GET_PATH = "/processo/{id}";
	private static final String CREATE_PATH = "/processo/criar";
	private static final String UPDATE_PATH = "/processo/{id}/alterar";
	private static final String GET_PROTOCOLO_PATH = "/processo/consultar?protocoloFormatado={protocoloFormatado}";
	
	public ProcessoService(SeiAccess access) {
		super(access);
	}
	
	public ProcessoService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@SuppressWarnings("unchecked")
	public Optional<Processo> get(Long id) {
		return get(GET_PATH, new ParameterizedTypeReference<SeiResponseEntity<Processo>>(){}, Map.of("id", id));
	}
	
	@SuppressWarnings("unchecked")
	public Optional<ProcessoFindResponse> get(String protocolo) {
		return get(GET_PROTOCOLO_PATH, new ParameterizedTypeReference<SeiResponseEntity<ProcessoFindResponse>>(){}, Map.of("protocoloFormatado", protocolo));
	}	

	@SuppressWarnings("unchecked")
	public Optional<ProcessoCreateResponse> create(ProcessoCreate processo) {
		return post(CREATE_PATH, processo, new ParameterizedTypeReference<SeiResponseEntity<ProcessoCreateResponse>>(){});
	}
	
	@SuppressWarnings("unchecked")
	public Optional<?> update(ProcessoUpdate processo) {
		return post(UPDATE_PATH, processo, Map.of("id", processo.getId()));
	}	
	
}
