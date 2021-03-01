package jus.trepe.br.sei.remote.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class SimNaoDeserializer extends JsonDeserializer<Boolean> {
	
	public static final String SIM = "S";
	public static final String NAO = "N";
	
	@Override
	public Boolean deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return parser.getText().equalsIgnoreCase(SIM);
	}
	
	@Override
	public Boolean getNullValue() {
		return Boolean.FALSE;
	}	

}
