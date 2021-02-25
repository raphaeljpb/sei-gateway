package jus.trepe.br.sei.dto.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;

public class SeiEntityDeserializer extends BeanDeserializer {

	private static final long serialVersionUID = 8141893533611650064L;
	
	public SeiEntityDeserializer() {
		super(null);
	}

	public SeiEntityDeserializer(BeanDeserializerBase src, BeanPropertyMap props) {
		super(src, props);
	}

	@Override
	public Object deserialize(JsonParser p, DeserializationContext ctxt, Object bean) throws IOException {
		return super.deserialize(p, ctxt, bean);
	}


}
