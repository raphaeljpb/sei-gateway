package jus.trepe.br.sei.remote.serialization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface InnerWrapperObject {
    String value();
}