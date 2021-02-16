package jus.trepe.br.sei.remote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SeiService<T> {
	
	@NonNull
	protected RestTemplate restTemplate;
	
	public abstract String getPath();
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}	
	Optional<T> getElement(Long id) {
		return Optional.empty();
	}
	List<T> getElements() {
		return List.of();
	}

}
