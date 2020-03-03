package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Cad_Projetos_Sesuite_RiscosOperacionais implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer sesuiteRiscoOperacionaisId;
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
	
	@OneToOne
	@JoinColumn(name="fk_projSesuiteId" ,foreignKey = @ForeignKey(name="fk_projSeseuite_riscoOperacionais"))
	@MapsId
	@Setter private Cad_Projetos_Sesuite sesuite;
	
}
