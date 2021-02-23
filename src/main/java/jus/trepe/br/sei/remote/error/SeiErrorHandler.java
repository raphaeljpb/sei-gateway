package jus.trepe.br.sei.remote.error;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class SeiErrorHandler extends DefaultResponseErrorHandler {
	

	@Override
	protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
		super.handleError(response, statusCode);
	}

}
