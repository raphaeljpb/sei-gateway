package jus.trepe.br.sei.remote.service;

import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jus.trepe.br.sei.dto.SeiResponseEntity;
import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.exception.SeiException;

public class AuthenticationService extends SeiService<Usuario> {

	static ObjectMapper userMapper;
	
	static {
		userMapper = new ObjectMapper();
		userMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public AuthenticationService(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public String getPath() {
		return "/autenticar";
	}

	public Optional<Usuario> post(Usuario usuario) {
		ResponseEntity<SeiResponseEntity<Usuario>> response = 
				this.getRestTemplate()
				 .exchange(getPath(), 
						 HttpMethod.POST, 
						 new HttpEntity<Usuario>(usuario),
					     new ParameterizedTypeReference<SeiResponseEntity<Usuario>>() {});
		if (response.getStatusCode() == HttpStatus.OK) {	
			verificaResponse(response.getBody());
			return Optional.ofNullable(response.getBody().getEntidade());
		} else {
			return Optional.empty();
		}
	}

	private void verificaResponse(SeiResponseEntity<Usuario> seiResponseEntity) {
		if (seiResponseEntity.hasErrors()) {
			throw new SeiException(seiResponseEntity.getMensagem());
		}
	}
	
	

}
