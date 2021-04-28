package jus.trepe.br.sei.dto.documento.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Data;

@Data
public class Documento {

	@JsonProperty("idDocumento")
	private Long id;
	@JsonProperty("nomeDocumento")
	private String nome;
	private String protocolo;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate dataElaboracao;

}
