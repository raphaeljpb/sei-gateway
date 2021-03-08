package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import jus.trepe.br.sei.remote.SeiResponseEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SeiService<T> {
	
	@NonNull
	protected RestTemplate restTemplate;
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}	
	
	protected Optional<T> post(String path, Object payload, ParameterizedTypeReference<SeiResponseEntity<T>> responseType, Map<String, ?> params) {
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		ObjectMapper mapper = new ObjectMapper();

		@SuppressWarnings("unchecked")
		Map<String, Object> maps = mapper.convertValue(payload, Map.class);
		form.setAll(maps);
	
		ResponseEntity<SeiResponseEntity<T>> response = 
				getRestTemplate()
				 .exchange(path, 
						 HttpMethod.POST, 
						 new HttpEntity<MultiValueMap<String, Object>>(form),
						 responseType,
						 params);
		
		if (response.getStatusCode() == HttpStatus.OK) {	
			verificaResponse(response.getBody());
			return Optional.ofNullable(response.getBody().getEntidade());
		} else {
			return Optional.empty();
		} 
	}
	
	protected Optional<T> post(String path, Object payload, ParameterizedTypeReference<SeiResponseEntity<T>> responseType) {
		return this.post(path, payload, responseType, Map.of());
	}
	
	protected Optional<?> post(String path, Object payload, Map<String, ?> params) {
		return this.post(path, payload, new ParameterizedTypeReference<SeiResponseEntity<T>>(){}, params);
	}	
	
	protected Optional<?> post(String path, Object payload) {
		return this.post(path, payload, Map.of());
	}
	
	protected Optional<T> get(String path, ParameterizedTypeReference<SeiResponseEntity<T>> responseType, Map<String, ?> params) {		
		ResponseEntity<SeiResponseEntity<T>> response = 
				getRestTemplate().exchange(path,
				HttpMethod.GET, null, responseType, params);
		if (response.getStatusCode() == HttpStatus.OK) {	
			verificaResponse(response.getBody());
			return Optional.ofNullable(response.getBody().getEntidade());
		} else {
			return Optional.empty();
		}
	}
	
	protected Optional<T> get(String path, ParameterizedTypeReference<SeiResponseEntity<T>> responseType) {
		return this.get(path, responseType, Map.of());
	}
	
	protected Optional<T> get(String path) {
		return this.get(path, new ParameterizedTypeReference<SeiResponseEntity<T>>(){}, Map.of());
	}
		
	
	private void verificaResponse(SeiResponseEntity<?> seiResponseEntity) {
		seiResponseEntity.validate();
	}

}
