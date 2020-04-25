package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Base_GSSOnLine_Perguntaserespostas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;
	@Getter @Setter private Integer ano;
	@Getter @Setter private String refexec;
	@Getter @Setter private Integer matricula;
	@Getter @Setter private Integer nrservicoorigem;
	@Getter @Setter private Integer nrtramite;
	@Getter @Setter private Integer nrservico;
	@Getter @Setter private String equipe;
	@Getter @Setter private Integer servico;
	@Getter @Setter private String descricao;
	@Getter @Setter private String setorexec;
	@Getter @Setter private String etapa;
	@Getter @Setter private String pergunta;
	@Getter @Setter private String codresposta;
	@Getter @Setter private String resposta;
	@Getter @Setter private String complemento;


}
