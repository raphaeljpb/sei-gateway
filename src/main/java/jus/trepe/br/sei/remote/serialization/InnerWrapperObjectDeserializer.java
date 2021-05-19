package jus.trepe.br.sei.remote.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class InnerWrapperObjectDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

	private JavaType wrappedType;
	private String wrapperKey;

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {
		InnerWrapperObject skipWrapperObject = property.getAnnotation(InnerWrapperObject.class);		
		wrapperKey = skipWrapperObject.value(); 
		wrappedType = property.getType();
		return this;
	}

	@Override
	public Object deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(parser);
		JsonNode wrapped = jsonNode.get(wrapperKey);
		Object mapped = null;
		if (wrapped != null) {
			if (jsonNode instanceof ObjectNode) {
				addRootAttributes((ObjectNode) jsonNode, wrapped);
			}
			mapped = mapIntoObject(wrapped);
		} else {
			mapped = mapIntoObject(jsonNode);
		}
		return mapped;
	}

	private void addRootAttributes(ObjectNode objectNode, JsonNode wrapped) {
		objectNode.remove(this.wrapperKey);
		objectNode.fields().forEachRemaining((entry) -> {
			((ObjectNode) wrapped).put(entry.getKey()+"_root", entry.getValue().asText());
		});
	}

	private Object mapIntoObject(JsonNode node) throws IOException, JsonProcessingException {
		JsonParser parser = node.traverse();
		
		return configureMapper().readValue(parser, wrappedType);
	}
	
	private ObjectMapper configureMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		SimpleModule module = new SimpleModule("BooleanDeserializerModule");
		module.addDeserializer(Boolean.class, new SimNaoDeserializer());
		module.addDeserializer(Boolean.TYPE, new SimNaoDeserializer());
		
		mapper.registerModule(module);
		
		return mapper;
	}

}
