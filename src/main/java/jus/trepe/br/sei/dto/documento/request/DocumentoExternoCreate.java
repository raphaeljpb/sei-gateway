package jus.trepe.br.sei.dto.documento.request;

import java.time.LocalDate;

import org.springframework.core.io.Resource;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jus.trepe.br.sei.dto.documento.TipoConferencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DocumentoExternoCreate extends DocumentoCreate {
	
	private String numero;
	@NonNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate dataElaboracao;
	@JsonProperty("idTipoConferencia")
	private TipoConferencia tipoConferencia;
	@NonNull
	@JsonIgnore
	private Resource anexo;
	
	@Override
	public MultiValueMap<String, Object> submitFields() {
		MultiValueMap<String, Object> form = super.submitFields();
		form.add("anexo", anexo);
		
		return form;
	}
}
