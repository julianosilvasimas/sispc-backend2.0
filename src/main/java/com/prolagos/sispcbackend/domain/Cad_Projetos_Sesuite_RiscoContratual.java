package com.prolagos.sispcbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Cad_Projetos_Sesuite_RiscoContratual extends Cad_Projetos_Sesuite {
	private static final long serialVersionUID = 1L;
	
	@Column(length=50)
	@Getter @Setter private String clausulacontratual;
	@Column(length=50)
	@Getter @Setter private String tipometa;
	@Column(length=50)
	@Getter @Setter private String metaatingida;
	@Column(length=50)
	@Getter @Setter private String penalidade12meses;
	@Column(length=50)
	@Getter @Setter private String penalidadeaplicavel;
	@Column(length=50)
	@Getter @Setter private String probabilidadepenalidade;
	@Column(length=50)
	@Getter @Setter private String postergacao;
	@Column(length=50)
	@Getter @Setter private String impactopolitico;

}
