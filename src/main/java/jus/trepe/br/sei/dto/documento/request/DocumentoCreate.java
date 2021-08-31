package jus.trepe.br.sei.dto.documento.request;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.HipoteseLegal;
import jus.trepe.br.sei.dto.Interessado;
import jus.trepe.br.sei.dto.Unidade;
import jus.trepe.br.sei.dto.documento.TipoDocumento;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.request.FormSubmission;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class DocumentoCreate implements FormSubmission {

	@JsonProperty("idUnidadeGeradoraProtocolo")
	@NonNull
	private Unidade unidadeGeradora;
	@NonNull
	@JsonProperty("idSerie")
	private TipoDocumento tipoDocumento;
	private List<Assunto> assuntos = new ArrayList<>();
	private List<Interessado> interessados = new ArrayList<>();
	@NonNull
	private String observacao;
	@NonNull
	private NivelAcesso nivelAcesso;
	@JsonProperty("idHipoteseLegal")
	private HipoteseLegal hipoteseLegal;
	
	public DocumentoCreate addAssunto(Assunto assunto) {
		assuntos.add(assunto);
		return this;
	}
	
	public DocumentoCreate addInteressado(Interessado interessado) {
		interessados.add(interessado);
		return this;
	}	
	
	@JsonGetter("assuntos")
	public String getAssuntos() {
		return assuntos.stream()				
				.map(assunto -> assunto.getId().toString())
				.collect(Collectors.joining(","));
	}
	
	@JsonGetter("interessados")
	public String getInteressados() {
		return interessados.stream()
				.map(interessado -> interessado.getId().toString())
				.collect(Collectors.joining(","));
	}	
	
}
