package com.prolagos.sispcbackend.domain;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "sesuiteId")
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
	
	@Column(name="ja_teve_investimento_realizado")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter protected Boolean teveinvestimento;
	
	@Column(length=50)
	@Getter @Setter protected String valorinvestido;
	
	@Column(length=50)
	@Getter @Setter protected String envolve;
	
	@Column(name="objetivo_atingido_independente_outro_nao_aprovado")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter protected Boolean objetivoindepende;
	
	@Column(length=50)
	@Getter @Setter protected String dependente;
	
	
	@Getter @Setter protected String tipo;
	
	@Column(name="diretamente_associado_corebusiness_empresa")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter protected Boolean corebusiness;
	
	@Column(name="associado_negocio_existente_aegea")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter protected Boolean negocioexistente;
	
	@Column(length=50)
	@Getter @Setter protected String principalmotivacao;
	
	@Column(name="facilita_melhora_contribui_operacoes_atuais")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter protected Boolean melhoraempresa;
	@Column(length=50)
	
	@Getter @Setter protected String delineado;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="sesuite")
	@Getter @Setter private Cad_Projetos_Sesuite_Beneficios  beneficios;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="sesuite")
	@Getter @Setter private Cad_Projetos_Sesuite_Direcionamento direcionamento;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="sesuite")
	@Getter @Setter private Cad_Projetos_Sesuite_LicencaSocial licenca;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="sesuite")
	@Getter @Setter private Cad_Projetos_Sesuite_RiscoContratual riscoscontratual;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="sesuite")
	@Getter @Setter private Cad_Projetos_Sesuite_RiscosOperacionais riscosoperacionais;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_sesuite"))
	@MapsId
	@Setter protected Cad_SisPC_Projetos projeto;
	
}
