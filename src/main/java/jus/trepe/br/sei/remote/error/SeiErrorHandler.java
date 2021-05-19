package jus.trepe.br.sei.remote.error;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jus.trepe.br.sei.remote.SeiResponseEntity;

public class SeiErrorHandler extends DefaultResponseErrorHandler {
	
	@Override
	protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
		if (List.of(HttpStatus.FORBIDDEN, HttpStatus.UNAUTHORIZED).contains(statusCode)) {			
			String jsonMessage = new String(response.getBody().readAllBytes());
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			@SuppressWarnings("rawtypes")
			SeiResponseEntity seiResponseEntity = objectMapper.readValue(jsonMessage, SeiResponseEntity.class);
			seiResponseEntity.validate();
		}
		super.handleError(response, statusCode);
	}

}
