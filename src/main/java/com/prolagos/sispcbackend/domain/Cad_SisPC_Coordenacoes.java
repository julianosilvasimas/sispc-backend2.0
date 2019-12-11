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
@EqualsAndHashCode(of = "coordenacaoId")
public class Cad_SisPC_Coordenacoes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer coordenacaoId;
	@Getter @Setter private String coordenacao;
	@Getter @Setter private String tag;
	
	@JsonIgnore
	@OneToMany(mappedBy="coordenacaoId")
	@Getter @Setter private List<Cad_SisPC_Tagsupervisoes> tagsupervisoes= new ArrayList<>();

	public Cad_SisPC_Coordenacoes(Integer coordenacaoId, String coordenacao, String tag) {
		super();
		this.coordenacaoId = coordenacaoId;
		this.coordenacao = coordenacao;
		this.tag = tag;
	}
	
	

}
