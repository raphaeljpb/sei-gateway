package jus.trepe.br.sei.remote.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.Processo;
import jus.trepe.br.sei.dto.SeiResponseEntity;
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
	
	public Optional<T> post(T object) {
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
	
	public Optional<T> get(Long id) {		
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
	
	List<T> list() {
		return List.of();
	}
	
	protected void verificaResponse(SeiResponseEntity<T> seiResponseEntity) {
		seiResponseEntity.validate();
	}

}
