package jus.trepe.br.sei.remote.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class SimNaoDeserializer extends JsonDeserializer<Boolean> {

	public static final String SIM = "S";
	public static final String NAO = "N";

	final protected Class<?> valueClass = Boolean.class;

	@Override
	public Boolean deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonToken token = parser.getCurrentToken();
		
		if (token == JsonToken.VALUE_TRUE) {
			return true;
		}
		if (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_NULL) {
			return false;
		}
		if (token == JsonToken.VALUE_NUMBER_INT) {
			return parser.getIntValue() != 0;
		}
		if (token == JsonToken.VALUE_STRING) {
			return parser.getText().trim().equalsIgnoreCase(SIM);
		}
		throw ctxt.weirdStringException(token.asString(), valueClass, 
				"Valor não identificado como Boolean");
	}

	@Override
	public Boolean getNullValue() {
		return Boolean.FALSE;
	}

}
