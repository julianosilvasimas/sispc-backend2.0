package com.prolagos.sispcbackend.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "sesuiteId")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // define estrategia de superclasse
public class Cad_Projetos_Sesuite implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter protected Integer sesuiteId;
	@Column(length=50)
	@Getter @Setter protected String cognosid;
	@Column(length=50)
	@Getter @Setter protected String nprojeto;
	@Column(length=50)
	@Getter @Setter protected String unidade;
	@Column(length=50)
	@Getter @Setter protected String escopo;
	@Column(length=50)
	@Getter @Setter protected String justificativa;
	@Column(length=50)
	@Getter @Setter protected String premissas;
	@Column(length=50)
	@Getter @Setter protected String nvengenharia;
	@Column(length=50)
	@Getter @Setter protected String responsavel;
	@Column(length=50)
	@Getter @Setter protected String preenchimento;
	@Column(length=50)
	@Getter @Setter protected String area;
	@Column(length=50)
	@Getter @Setter protected String email;
	@Column(length=50)
	@Getter @Setter protected String tel;
	@Column(length=50)
	@Getter @Setter protected String teveinvestimento;
	@Column(length=50)
	@Getter @Setter protected String envolve;
	@Column(length=50)
	@Getter @Setter protected String tipo;
	@Column(length=50)
	@Getter @Setter protected String corebusiness;
	@Column(length=50)
	@Getter @Setter protected String negocioexistente;
	@Column(length=50)
	@Getter @Setter protected String principalmotivacao;
	@Column(length=50)
	@Getter @Setter protected String melhoraempresa;
	@Column(length=50)
	@Getter @Setter protected String delineado;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_sesuite"))
	@MapsId
	@Setter protected Cad_SisPC_Projetos projeto;
	
}
