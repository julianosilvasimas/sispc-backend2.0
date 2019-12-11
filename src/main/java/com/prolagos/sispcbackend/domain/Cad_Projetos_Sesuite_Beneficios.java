package com.prolagos.sispcbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Cad_Projetos_Sesuite_Beneficios extends Cad_Projetos_Sesuite {
	private static final long serialVersionUID = 1L;
	
	@Column(length=50)
	@Getter @Setter private String impactogestao;
	@Column(length=50)
	@Getter @Setter private String impactomotivacao;
	@Column(length=50)
	@Getter @Setter private String impactoseguranca;
	@Column(length=50)
	@Getter @Setter private String impactosustentabilidade;
	@Column(length=50)
	@Getter @Setter private String outramelhoria;
	@Column(length=50)
	@Getter @Setter private String outramelhoriaespec;

}
