package jus.trepe.br.sei.dto.processo.request;

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
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.TipoProcesso;
import jus.trepe.br.sei.dto.request.FormSubmission;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
public class ProcessoUpdate implements FormSubmission {
	@NonNull
	private Long id;
	@NonNull
	private List<Assunto> assuntos = new ArrayList<>();
	private List<Interessado> interessados = new ArrayList<>();
	private String especificacao;
	private String observacao;
	@JsonProperty("idTipoProcesso")
	@NonNull
	private TipoProcesso tipo;
	@NonNull
	private NivelAcesso nivelAcesso;
	@JsonProperty("idHipoteseLegal")
	private HipoteseLegal hipoteseLegal;
	
	public ProcessoUpdate addAssunto(Assunto assunto) {
		assuntos.add(assunto);
		return this;
	}
	
	public ProcessoUpdate addInteressado(Interessado interessado) {
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
