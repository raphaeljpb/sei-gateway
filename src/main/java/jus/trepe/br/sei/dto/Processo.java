package jus.trepe.br.sei.dto;

import lombok.Data;

@Data
public class Processo {
    /**
     * Identificador do tipo de procedimento no SEI
     */
    String idTipoProcedimento;
    /**
     * Número do procedimento, se não for informado o sistema irá gerar um novo número automaticamente
     */
    String numeroProtocolo;
    /**
     * Data de autuação do procedimento, se não for informada o sistema utilizará a data atual
     */
    String dataAutuacao;
    /**
     * Especificacao Especificação do procedimento
     */
    String especificacao;
    /**
     * Assuntos do procedimento (ver {@link Assunto}). Os assuntos informados serão adicionados aos sugeridos para o
     * tipo de procedimento. Passar array vazio se nenhum outro assunto for necessário (caso apenas os sugeridos para o
     * tipo bastem para classificação).
     */
    Assunto[] assuntos;
    /**
     * Interessados do procedimento (ver {@link Interessado}). Se não existirem interessados informar array vazio.
     */
    Interessado[] interessados;
    /**
     * Texto da observação da unidade, passar <code>null</code> se não existir
     */
    String observacao;
    /**
     * Nível de acesso do procedimento
     * <ul>
     * <li>0 - Público;
     * <li>1 - Restrito;
     * <li>2 - Sigiloso;
     * <li>9 - Sugeridos para o tipo do processo, conforme seu cadastro no SEI.
     * <li>Null - O processo assumirá o nível de acesso e hipótese legal;
     * </ul>
     */
    String nivelAcesso;
    /**
     * IdHipoteseLegal Identificador da hipótese legal associada
     */
    String idHipoteseLegal;   
}
