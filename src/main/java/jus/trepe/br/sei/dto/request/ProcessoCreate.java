package jus.trepe.br.sei.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jus.trepe.br.sei.dto.Assunto;
import jus.trepe.br.sei.dto.HipoteseLegal;
import jus.trepe.br.sei.dto.Interessado;
import jus.trepe.br.sei.dto.processo.NivelAcesso;
import jus.trepe.br.sei.dto.processo.TipoProcesso;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ProcessoCreate {

	@NonNull
	private List<Assunto> assuntos = new ArrayList<>();
	private List<Interessado> interessados = new ArrayList<>();;
	private String especificacao;
	private String observacoes;
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
