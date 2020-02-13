
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
@EqualsAndHashCode(of = "supervisaoId")
public class Cad_SisPC_Supervisoes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer supervisaoId;
	@Getter @Setter private String codsupervisao;
	@Getter @Setter private String supervisao;

	@JsonIgnore
	@OneToMany(mappedBy="supervisaoId")
	@Getter @Setter private List<Cad_SisPC_Tagsupervisoes> tagsupervisoes= new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="supervisaoId")
	@Getter @Setter private List<Cad_SisPC_Usuarios> usuarios= new ArrayList<>();	

	public Cad_SisPC_Supervisoes(Integer supervisaoId, String codsupervisao, String supervisao) {
		super();
		this.supervisaoId = supervisaoId;
		this.codsupervisao = codsupervisao;
		this.supervisao = supervisao;
	}
	
}
