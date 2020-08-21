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
public class Cad_Projetos_Sesuite_Direcionamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer sesuiteDirecionamentoId;
	@Column(length=100)
	@Getter @Setter private String nagua;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter private Boolean impactoagua;
	@Column(length=100)
	@Getter @Setter private String nesgoto;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter private Boolean impactoesgoto;
	
	@Getter @Setter private String maturidade;
	@Column(length=100)
	@Getter @Setter private String modelomercado;
	@Column(length=100)
	@Getter @Setter private String diferencialcompetitivo;
	@Column(length=100)
	@Getter @Setter private String modeloconcessao;
	@Column(length=100)
	@Getter @Setter private String sinergia;
	@Column(length=100)
	@Getter @Setter private String maturidaderegiao;
	
	@OneToOne
	@JoinColumn(name="fk_projSesuiteId" ,foreignKey = @ForeignKey(name="fk_projSeseuite_direcionamento"))
	@MapsId
	@Setter private Cad_Projetos_Sesuite sesuite;
	
	
}
