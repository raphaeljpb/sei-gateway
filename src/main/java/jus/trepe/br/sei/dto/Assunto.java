package jus.trepe.br.sei.dto;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Assunto {

	public static final List<Assunto> ASSUNTOS;
	private int id;
	@JsonIgnore
	private String descricao;

	static {
		ASSUNTOS = Arrays.asList(new Assunto(1, "ADMINISTRAÇÃO GERAL"),
				new Assunto(2, "MODERNIZAÇÃO E REFORMA ADMINISTRATIVA (inclusive Projetos, Estudos e Normas)"),
				new Assunto(5, "ACORDOS. AJUSTES. CONTRATOS. CONVÊNIOS (Inclusive formalização, execução, acompanhamento, fiscalização, prestação de contas, tomada de contas e tomada de contas especial de convênios, contratos de repasse, termos de parceria e termos de cooperação)"));
	}

	public Assunto(int id) {
		this(id, null);
	}

	public Assunto(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

}
