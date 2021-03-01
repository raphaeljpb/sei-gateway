package jus.trepe.br.sei.dto.processo;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jus.trepe.br.sei.remote.serialization.SimNaoDeserializer;
import lombok.Data;

@Data
public class Informacoes {
	
	@JsonDeserialize(using = SimNaoDeserializer.class)
	private boolean documentoSigiloso;
	private boolean documentoRestrito;
	private boolean documentoNovo;
	private boolean documentoPublicado;
	private boolean anotacao;
	private boolean anotacaoPrioridade;
	@JsonDeserialize(using = SimNaoDeserializer.class)	
	private boolean ciencia;
	private boolean retornoProgramado;
    private LocalDate retornoData;;
    private boolean retornoAtrasado;
    private boolean processoAcessadoUsuario;
    private boolean processoAcessadoUnidade;
    private boolean processoRemocaoSobrestamento;
    private boolean processoBloqueado;
    private boolean processoDocumentoIncluidoAssinado;
    private boolean processoPublicado;
//    nivelAcessoGlobal
    private boolean podeGerenciarCredenciais;
    private boolean processoAberto;
    private boolean processoEmTramitacao;
    private boolean processoSobrestado;
    private boolean processoAnexado;
    private boolean podeReabrirProcesso;
    private boolean podeRegistrarAnotacao;
    private boolean podeRemoverSobrestamento;
//    tipo
//    processoGeradoRecebido	

}
