package jus.trepe.br.sei.dto.processo.request;

import java.util.ArrayList;
import java.util.List;

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
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ProcessoCreate implements FormSubmission {

	@NonNull
	private List<Assunto> assuntos = new ArrayList<>();
	private List<Interessado> interessados = new ArrayList<>();
	private String especificacao;
	@JsonProperty("observacoes")
	private String observacao;
	@NonNull
	@JsonProperty("tipoProcesso")
	private TipoProcesso tipo;
	@NonNull
	private NivelAcesso nivelAcesso;
	private HipoteseLegal hipoteseLegal;
	
	public ProcessoCreate addAssunto(Assunto assunto) {
		assuntos.add(assunto);
		
		return this;
	}
	
	public ProcessoCreate addInteressado(Interessado interessado) {
		interessados.add(interessado);
		
		return this;
	}	
	
	
}
