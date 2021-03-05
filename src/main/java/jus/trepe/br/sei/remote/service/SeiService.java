package jus.trepe.br.sei.remote.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
	
	public abstract Map<HttpMethod, String> getPaths();
	
	public abstract ParameterizedTypeReference<SeiResponseEntity<T>> getParameterizedTypeReference();
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}	
	
	protected Optional<T> post(T object) {
		
		ResponseEntity<SeiResponseEntity<T>> response = 
				getRestTemplate()
				 .exchange(getPaths().get(HttpMethod.POST), 
						 HttpMethod.POST, 
						 new HttpEntity<T>(object),
					     getParameterizedTypeReference());
		if (response.getStatusCode() == HttpStatus.OK) {	
			verificaResponse(response.getBody());
			return Optional.ofNullable(response.getBody().getEntidade());
		} else {
			return Optional.empty();
		}		
	}
	
	@SuppressWarnings("rawtypes")
	protected Optional<?> post(String path, Object payload, Map<String, ?> params) {
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		ObjectMapper mapper = new ObjectMapper();

		@SuppressWarnings("unchecked")
		Map<String, Object> maps = mapper.convertValue(payload, Map.class);
		form.setAll(maps);
	
		ResponseEntity<SeiResponseEntity<Map>> response = 
				getRestTemplate()
				 .exchange(path, 
						 HttpMethod.POST, 
						 new HttpEntity<MultiValueMap<String, Object>>(form),
						 new ParameterizedTypeReference<SeiResponseEntity<Map>>(){},
						 params);
		
		if (response.getStatusCode() == HttpStatus.OK) {	
			verificaResponse((SeiResponseEntity<Map>) response.getBody());
			return Optional.ofNullable(response.getBody().getEntidade());
		} else {
			return Optional.empty();
		} 
	}
	
	protected Optional<?> post(String path, Object payload) {
		return this.post(path, payload, Map.of());
	}
		
	
	protected Optional<T> get(Long id) {		
		ResponseEntity<SeiResponseEntity<T>> response = 
				getRestTemplate().exchange(getPaths().get(HttpMethod.GET),
				HttpMethod.GET, null, getParameterizedTypeReference(), id);
		if (response.getStatusCode() == HttpStatus.OK) {	
			verificaResponse(response.getBody());
			return Optional.ofNullable(response.getBody().getEntidade());
		} else {
			return Optional.empty();
		}
	}
	
	protected void verificaResponse(SeiResponseEntity<?> seiResponseEntity) {
		seiResponseEntity.validate();
	}

}
