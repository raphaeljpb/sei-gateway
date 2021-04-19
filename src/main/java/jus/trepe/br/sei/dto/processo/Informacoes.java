package jus.trepe.br.sei.dto.processo;

import java.util.Date;

import lombok.Data;

@Data
public class Informacoes {
	
	private boolean documentoSigiloso;
	private boolean documentoRestrito;
	private boolean documentoNovo;
	private boolean documentoPublicado;
	private boolean anotacao;
	private boolean anotacaoPrioridade;
	private boolean ciencia;
	private boolean retornoProgramado;
    private boolean retornoAtrasado;
    private boolean processoAcessadoUsuario;
    private boolean processoAcessadoUnidade;
    private boolean processoRemocaoSobrestamento;
    private boolean processoBloqueado;
    private boolean processoDocumentoIncluidoAssinado;
    private boolean processoPublicado;
    private boolean podeGerenciarCredenciais;
    private boolean processoAberto;
    private boolean processoEmTramitacao;
    private boolean processoSobrestado;
    private boolean processoAnexado;
    private boolean podeReabrirProcesso;
    private boolean podeRegistrarAnotacao;
    private boolean podeRemoverSobrestamento;
    private Date retornoData;
    private NivelAcesso nivelAcessoGlobal;

}
