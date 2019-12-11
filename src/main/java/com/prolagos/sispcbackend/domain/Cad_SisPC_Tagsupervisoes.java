package com.prolagos.sispcbackend.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "tagsupervisaoId")
public class Cad_SisPC_Tagsupervisoes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer tagsupervisaoId;
	@Getter @Setter private String tagsupervisao;
	
	@ManyToOne
	@JoinColumn(name="fk_taggerenciaId", foreignKey = @ForeignKey(name="fk_tagsupervisao_taggerencias"))
	@Getter @Setter private Cad_SisPC_Taggerencias taggerenciaId;
	
	@ManyToOne
	@JoinColumn(name="fk_coordenacaoId", foreignKey = @ForeignKey(name="fk_tagsupervisao_coordenacoes"))
	@Getter @Setter private Cad_SisPC_Coordenacoes coordenacaoId;
	
	@ManyToOne
	@JoinColumn(name="fk_supervisaoId", foreignKey = @ForeignKey(name="fk_tagsupervisao_supervisoes"))
	@Getter @Setter private Cad_SisPC_Coordenacoes supervisaoId;
	
	@JsonIgnore
	@OneToMany(mappedBy="tagsupervisaoId")
	@Getter @Setter private List<Cad_Auth_Usuarios> authorizations = new ArrayList<>();

}
