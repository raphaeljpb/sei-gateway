package jus.trepe.br.sei.remote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jus.trepe.br.sei.dto.SeiResponseEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SeiService<T> {
	
	@NonNull
	protected RestTemplate restTemplate;
	
	public abstract String getPath();
	
	public abstract ParameterizedTypeReference<SeiResponseEntity<T>> getParameterizedTypeReference();
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}	
	
	public Optional<T> post(T object) {
		ResponseEntity<SeiResponseEntity<T>> response = 
				this.getRestTemplate()
				 .exchange(getPath(), 
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
		return Optional.empty();
	}
	
	List<T> list() {
		return List.of();
	}
	
	protected void verificaResponse(SeiResponseEntity<T> seiResponseEntity) {
		seiResponseEntity.validate();
	}

}
