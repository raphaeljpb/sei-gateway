package jus.trepe.br.sei.remote.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.documento.request.DocumentoCreate;
import jus.trepe.br.sei.dto.documento.request.DocumentoExternoCreate;
import jus.trepe.br.sei.dto.documento.request.DocumentoInternoCreate;
import jus.trepe.br.sei.dto.documento.request.DocumentoSign;
import jus.trepe.br.sei.dto.documento.response.Documento;
import jus.trepe.br.sei.dto.documento.response.DocumentoCreateResponse;
import jus.trepe.br.sei.dto.documento.response.DocumentoListResponse;
import jus.trepe.br.sei.remote.SeiResponseEntity;
import lombok.NonNull;

@SuppressWarnings("rawtypes")
public class DocumentoService extends SeiService {
	
	private static final String LIST_DOCUMENTOS_BY_PROCESSO_PATH = "/documento/listar/{processo}";
	private static final String VIEW_DOCUMENTO_PATH = "/documento/{id}/interno/visualizar";
	private static final String GET_DOCUMENTO_PATH = "/documento/{tipo}/consultar/{id}";
	private static final String CREATE_DOCUMENTO_PATH = "/documento/{processo}/{tipo}/criar";
	private static final String DOWNLOAD_DOCUMENTO_PATH = "/documento/baixar/anexo/{id}";
	private static final String SIGN_DOCUMENTO_PATH = "/documento/assinar";

	public DocumentoService(@NonNull RestTemplate restTemplate) {
		super(restTemplate);
	}
	
	@SuppressWarnings("unchecked")
	public Optional<List<DocumentoListResponse>> list(Long idProcesso) {
		return get(LIST_DOCUMENTOS_BY_PROCESSO_PATH, 
				new ParameterizedTypeReference<SeiResponseEntity<List<DocumentoListResponse>>>(){}, Map.of("processo", idProcesso));
	}
	
	public Optional<DocumentoCreateResponse> create(Long idProcesso, DocumentoInternoCreate documento) {
		return create(idProcesso, documento, "interno");
	}
	
	public Optional<DocumentoCreateResponse> create(Long idProcesso, DocumentoExternoCreate documento) {
		return create(idProcesso, documento, "externo");
	}	
	
	@SuppressWarnings("unchecked")
	private Optional<DocumentoCreateResponse> create(Long idProcesso, DocumentoCreate documento, String path) {
		return post(CREATE_DOCUMENTO_PATH, documento, 
				new ParameterizedTypeReference<SeiResponseEntity<DocumentoCreateResponse>>(){}, 
				Map.of("processo", idProcesso, "tipo", path));
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Documento> get(Long id, TipoDocumento tipoDocumento) {
		return get(GET_DOCUMENTO_PATH,  
				new ParameterizedTypeReference<SeiResponseEntity<Documento>>(){}, 
				Map.of("tipo", tipoDocumento.getPath(), "id", id));
	}
	
	public byte[] download(Long id) {
		return getRestTemplate().getForObject(DOWNLOAD_DOCUMENTO_PATH, byte[].class, Map.of("id", id));		
	}
	
	@SuppressWarnings("unchecked")
	public Optional<String> viewContent(Long id) {
		return get(VIEW_DOCUMENTO_PATH, new ParameterizedTypeReference<SeiResponseEntity<String>>(){}, Map.of("id", id));
	}
	
	public Optional<?> sign(DocumentoSign documentoSign) {
		return post(SIGN_DOCUMENTO_PATH, documentoSign);
	}

}
