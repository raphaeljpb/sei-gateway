package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.request.FormSubmission;
import jus.trepe.br.sei.remote.SeiAccess;
import jus.trepe.br.sei.remote.SeiResponseEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SeiService<T> {
	
	@NonNull
	protected RestTemplate restTemplate;
	
	public SeiService(SeiAccess access) {
		this(access.buildTemplate(new RestTemplateBuilder()));
	}
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}
	
	protected Optional<T> post(String path, FormSubmission payload, ParameterizedTypeReference<SeiResponseEntity<T>> responseType, Map<String, ?> params) {
		return post(path, payload, responseType, params, MediaType.MULTIPART_FORM_DATA);
	}
	
	protected Optional<T> post(String path, FormSubmission payload, ParameterizedTypeReference<SeiResponseEntity<T>> responseType, Map<String, ?> params, MediaType formType) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(formType);
	
		ResponseEntity<SeiResponseEntity<T>> response = 
				getRestTemplate()
				 .exchange(path, 
						 HttpMethod.POST, 
						 new HttpEntity<MultiValueMap<String, Object>>(payload.submitFields(), headers),
						 responseType,
						 params);
		
		return verificaResponse(response);
	}
	
	protected Optional<T> post(String path, FormSubmission payload, ParameterizedTypeReference<SeiResponseEntity<T>> responseType) {
		return this.post(path, payload, responseType, Map.of());
	}
	
	protected Optional<?> post(String path, FormSubmission payload, Map<String, ?> params) {
		return this.post(path, payload, new ParameterizedTypeReference<SeiResponseEntity<T>>(){}, params);
	}	
	
	protected Optional<?> post(String path, FormSubmission payload) {
		return this.post(path, payload, Map.of());
	}
	
	protected Optional<T> get(String path, Map<String, ?> payload, 
			ParameterizedTypeReference<SeiResponseEntity<T>> responseType, Map<String, ?> params) {		
		ResponseEntity<SeiResponseEntity<T>> response = 
				getRestTemplate().exchange(path,
				HttpMethod.GET, new HttpEntity<Map<String, ?>>(payload), responseType, params);
		return verificaResponse(response);
	}
	
	protected Optional<T> get(String path, Map<String, ?> payload, ParameterizedTypeReference<SeiResponseEntity<T>> responseType) {
		return this.get(path, payload, responseType, Map.of());
	}	
	
	protected Optional<T> get(String path, ParameterizedTypeReference<SeiResponseEntity<T>> responseType, Map<String, ?> params) {
		return this.get(path, null, responseType, params);
	}	
	
	protected Optional<T> get(String path, ParameterizedTypeReference<SeiResponseEntity<T>> responseType) {
		return this.get(path, null, responseType, Map.of());
	}
	
	protected Optional<T> get(String path, Map<String, ?> params) {
		return this.get(path, new ParameterizedTypeReference<SeiResponseEntity<T>>(){}, params);
	}	
	
	protected Optional<T> get(String path) {
		return this.get(path, null, new ParameterizedTypeReference<SeiResponseEntity<T>>(){}, Map.of());
	}
		
	
	private Optional<T> verificaResponse(ResponseEntity<SeiResponseEntity<T>> response) {
		if (response.getStatusCode() == HttpStatus.OK) {	
			response.getBody().validate();
			return Optional.ofNullable(response.getBody().getEntidade());
		} else {
			return Optional.empty();
		}		
	}

}
