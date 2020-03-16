package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

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
public class Cad_Projetos_Sesuite_RiscoContratual implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer sesuiteRiscoContratualId;
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
	
	@OneToOne
	@JoinColumn(name="fk_projSesuiteId" ,foreignKey = @ForeignKey(name="fk_projSeseuite_riscoContratual"))
	@MapsId
	@Setter private Cad_Projetos_Sesuite sesuite;
	

}
