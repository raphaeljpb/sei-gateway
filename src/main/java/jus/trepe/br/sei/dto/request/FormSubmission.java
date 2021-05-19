package jus.trepe.br.sei.dto.request;

import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface FormSubmission {
	
	public default MultiValueMap<String, Object> submitFields() {
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		ObjectMapper mapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> maps = mapper.convertValue(this, Map.class);
		form.setAll(maps);		
		
		return form;
	}

}
