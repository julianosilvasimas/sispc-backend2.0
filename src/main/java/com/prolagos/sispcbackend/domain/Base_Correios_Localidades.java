package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "LOC_NU")
public class Base_Correios_Localidades implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer LOC_NU;
	@Getter @Setter private String UFE_SG;
	@Getter @Setter private String LOC_NO;
	@Getter @Setter private String CEP;
	@Getter @Setter private String LOC_IN_SIT;
	@Getter @Setter private String LOC_IN_TIPO_LOC;
	@Getter @Setter private String LOC_NU_SUB;
	@Getter @Setter private String LOC_NO_ABREV;
	@Getter @Setter private String MUN_NU;
	
	@JsonIgnore
	@OneToMany(mappedBy="LOC_NU")
	@Getter @Setter private List<Base_Correios_Bairros> bairros = new  ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="LOC_NU")
	@Getter @Setter private List<Base_Correios_Logradouros> logradouros = new  ArrayList<>();
	
	//@ManyToMany(mappedBy="localidade")
	//@Getter @Setter private List<Ind_Indicador> indicadores = new  ArrayList<>();
	
	
	public Base_Correios_Localidades(Integer lOC_NU, String uFE_SG, String lOC_NO, String cEP, String lOC_IN_SIT,
			String lOC_IN_TIPO_LOC, String lOC_NU_SUB, String lOC_NO_ABREV, String mUN_NU) {
		super();
		LOC_NU = lOC_NU;
		UFE_SG = uFE_SG;
		LOC_NO = lOC_NO;
		CEP = cEP;
		LOC_IN_SIT = lOC_IN_SIT;
		LOC_IN_TIPO_LOC = lOC_IN_TIPO_LOC;
		LOC_NU_SUB = lOC_NU_SUB;
		LOC_NO_ABREV = lOC_NO_ABREV;
		MUN_NU = mUN_NU;
	}
	

}
