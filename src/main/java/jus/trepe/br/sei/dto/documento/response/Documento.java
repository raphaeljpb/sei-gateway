package jus.trepe.br.sei.dto.documento.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jus.trepe.br.sei.dto.HipoteseLegal;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import lombok.Data;

@Data
public class Documento {

	@JsonProperty("idDocumento")
	private Long id;
	@JsonProperty("nomeDocumento")
	private String nome;
	private String protocolo;
	private String numero;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate dataElaboracao;
	private NivelAcesso nivelAcesso;
	private HipoteseLegal hipoteseLegal = new HipoteseLegal();
	
	@JsonSetter(value="idHipoteseLegal", nulls=Nulls.AS_EMPTY)
	private void configuraId(Integer id) {
		hipoteseLegal.setId(id);
	}
	
	@JsonSetter(value="nomeHipoteseLegal", nulls=Nulls.AS_EMPTY)
	private void configuraNomeHipoteseLegal(String nome) {
		hipoteseLegal.setNome(nome);
	}	
	
	@JsonSetter(value="baseLegal", nulls=Nulls.AS_EMPTY)
	private void configuraBaseHipoteseLegal(String baseLegal) {
		hipoteseLegal.setBaseLegal(baseLegal);
	}
	

}
