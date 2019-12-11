package com.prolagos.sispcbackend.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value="U")
public class Cad_Projetos_Sesuite_Direcionamento extends Cad_Projetos_Sesuite {
	private static final long serialVersionUID = 1L;
	
	@Column(length=50)
	@Getter @Setter private String nagua;
	@Column(length=50)
	@Getter @Setter private String impactoagua;
	@Column(length=50)
	@Getter @Setter private String nesgoto;
	@Column(length=50)
	@Getter @Setter private String impactoesgoto;
	@Column(length=50)
	@Getter @Setter private String maturidade;
	@Column(length=50)
	@Getter @Setter private String modelomercado;
	@Column(length=50)
	@Getter @Setter private String diferencialcompetitivo;
	@Column(length=50)
	@Getter @Setter private String modeloconcessao;
	@Column(length=50)
	@Getter @Setter private String sinergia;
	@Column(length=50)
	@Getter @Setter private String maturidaderegiao;
	
	
}
