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
	
	public Optional<T> post(T object) {
		return Optional.empty();
	}
	
	public Optional<T> get(Long id) {
		return Optional.empty();
	}
	
	List<T> list() {
		return List.of();
	}

}
