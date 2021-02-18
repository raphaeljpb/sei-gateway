package jus.trepe.br.sei.remote.service;

import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jus.trepe.br.sei.dto.Usuario;

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

	@Override
	public
	Optional<Usuario> post(Usuario usuario) {
		return Optional.ofNullable(this.getRestTemplate()
										 .execute(getPath(), 
												 HttpMethod.POST, 
												 (request) -> {
													 userMapper.writeValue(request.getBody(), usuario);
												 },
												 (response) -> {
													 JsonNode dados = userMapper.readTree(response.getBody());
													 System.out.println(dados);
													 System.out.println(dados.get("dados"));
													 return userMapper.treeToValue(dados.get("dados"), Usuario.class);
												 }));
				
	}

}
