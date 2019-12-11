package com.prolagos.sispcbackend.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Cad_Projetos_Sesuite_RiscosOperacionais extends Cad_Projetos_Sesuite {
	private static final long serialVersionUID = 1L;
	
	@Column(length=50)
	@Getter @Setter private String impactointerrupcao;
	@Column(length=50)
	@Getter @Setter private String probabilidadeimpactointerrupcao;
	@Column(length=50)
	@Getter @Setter private String custointerrupcao;
	@Column(length=50)
	@Getter @Setter private String complexidadeexecucao;
	@Column(length=50)
	@Getter @Setter private String impactosubstituicao;
	@Column(length=50)
	@Getter @Setter private String probabilidadeimpactosubstituicao;
	@Column(length=50)
	@Getter @Setter private String impactoambiental;
	@Column(length=50)
	@Getter @Setter private String probabilidadeimpactoambiental;
	@Column(length=50)
	@Getter @Setter private String impactointegridade;
	@Column(length=50)
	@Getter @Setter private String probabilidadeimpactointegridade;
	@Column(length=50)
	@Getter @Setter private String riscoatraso;
	@Column(length=50)
	@Getter @Setter private String possuilicenca;
	@Column(length=50)
	@Getter @Setter private String nlicenca;
	@Column(length=50)
	@Getter @Setter private String prazolicenca;
	@Column(length=50)
	@Getter @Setter private String condicionantelicenca;
	@Column(length=50)
	@Getter @Setter private String condicionantelicencaespec;
	@Getter @Setter private LocalDate emissaolicenca;
	@Getter @Setter private LocalDate validadelicenca;
	
}
