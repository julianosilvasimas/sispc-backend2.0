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
@EqualsAndHashCode(of = "gerenciaId")
public class Cad_SisPC_Gerencias implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer gerenciaId;
	@Getter @Setter private String gerencia;
	@Getter @Setter private String tag;
	
	@JsonIgnore
	@OneToMany(mappedBy="gerenciaId")
	@Getter @Setter private List<Cad_SisPC_Taggerencias> taggerencias= new ArrayList<>();

	public Cad_SisPC_Gerencias(Integer gerenciaId, String gerencia, String tag) {
		super();
		this.gerenciaId = gerenciaId;
		this.gerencia = gerencia;
		this.tag = tag;
	}

	
}
