package com.prolagos.sispcbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Cad_Projetos_Sesuite_LicencaSocial extends Cad_Projetos_Sesuite {
	private static final long serialVersionUID = 1L;
	
	@Column(length=50)
	@Getter @Setter private String impactosocial;
	@Column(length=50)
	@Getter @Setter private String relacionamentopolitico;
	@Column(length=50)
	@Getter @Setter private String relacionamentosociedade;
	@Column(length=50)
	@Getter @Setter private String impactoimagem;
	@Column(length=50)
	@Getter @Setter private String probabilidadeimpactoimagem;
	@Column(length=50)
	@Getter @Setter private String impactoreputacional;

	
}
