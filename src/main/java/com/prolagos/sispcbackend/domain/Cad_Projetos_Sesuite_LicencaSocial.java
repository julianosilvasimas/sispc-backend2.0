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
public class Cad_Projetos_Sesuite_LicencaSocial implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer sesuiteLicencaId;
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
	
	@OneToOne
	@JoinColumn(name="fk_projSesuiteId" ,foreignKey = @ForeignKey(name="fk_projSeseuite_licenca"))
	@MapsId
	@Setter private Cad_Projetos_Sesuite sesuite;

	
}
