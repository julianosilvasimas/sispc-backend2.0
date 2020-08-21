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
@EqualsAndHashCode(of = "IdEquipamento")
public class Cad_Energia_Gestal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer IdEquipamento;
	
	@Getter @Setter private String NomeDoEquipamento;
	@Getter @Setter private String BaseDeDados;
	@Getter @Setter private Integer IndicadorVolume;
	@Getter @Setter private Integer IndicadorKw;
	@Getter @Setter private Integer IndicadorRs;
	@Getter @Setter private Integer Indicadorrsm3;
	@Getter @Setter private Integer Indicadorkwm3;
	@Getter @Setter private Integer Classificacao;
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy="Unidade")
	@Setter private List<Apprpa_Energia_Gestal> Historic= new ArrayList<>();
	
	
}
