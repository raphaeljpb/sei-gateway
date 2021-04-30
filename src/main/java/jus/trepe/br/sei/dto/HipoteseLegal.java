package jus.trepe.br.sei.dto;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
public class HipoteseLegal {

	@JsonValue
	private int id;
	private String nome;
	public static final List<HipoteseLegal> HIPOTESES;
	
	static {
		HIPOTESES = Arrays.asList(
				new HipoteseLegal(1, "Controle Interno"),
				new HipoteseLegal(2, "Direito Autoral"),
				new HipoteseLegal(3, "Documento Preparatório"),
				new HipoteseLegal(4, "Informação Pessoal"),
				new HipoteseLegal(5, "Informações Privilegiadas de Sociedades Anônimas"),
				new HipoteseLegal(6, "Interceptação de Comunicações Telefônicas"),
				new HipoteseLegal(7, "Investigação de Responsabilidade de Servidor"),
				new HipoteseLegal(8, "Livros e Registros Contábeis Empresariais"),
				new HipoteseLegal(9, "Operações Bancárias"),
				new HipoteseLegal(10, "Proteção da Propriedade Intelectual de Software"),
				new HipoteseLegal(11, "Protocolo -Pendente Análise de Restrição de Acesso"),
				new HipoteseLegal(12, "Segredo de Justiça no Processo Civil"),
				new HipoteseLegal(13, "Segredo de Justiça no Processo Penal"),
				new HipoteseLegal(14, "Segredo Industrial"),
				new HipoteseLegal(15, "Sigilo das Comunicações"),
				new HipoteseLegal(16, "Sigilo de Empresa em Situação Falimentar"),
				new HipoteseLegal(17, "Sigilo do Inquérito Policial"),
				new HipoteseLegal(18, "Situação Econômico-Financeira de Sujeito Passivo"));
	}
	
	public HipoteseLegal(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
}
