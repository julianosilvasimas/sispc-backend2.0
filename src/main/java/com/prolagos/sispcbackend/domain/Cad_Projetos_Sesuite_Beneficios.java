package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Cad_Projetos_Sesuite_Beneficios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer sesuiteBeneficiosId;
	@Column(length=150)
	@Getter @Setter private String impactogestao;
	@Column(length=150)
	@Getter @Setter private String impactomotivacao;
	@Column(length=150)
	@Getter @Setter private String impactoseguranca;
	@Column(length=150)
	@Getter @Setter private String impactosustentabilidade;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter private Boolean outramelhoria;
	@Column(length=150)
	@Getter @Setter private String outramelhoriaespec;
	
	@OneToOne
	@JoinColumn(name="fk_projSesuiteId" ,foreignKey = @ForeignKey(name="fk_projSeseuite_beneficios"))
	@MapsId
	@Setter private Cad_Projetos_Sesuite sesuite;

}
