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
@EqualsAndHashCode(of = "moduloId")
public class Cad_SisPC_Modulos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer moduloId;
	@Getter @Setter private String modulo;
	@Getter @Setter private String nivel;
	
	@JsonIgnore
	@OneToMany(mappedBy="moduloId")
	@Getter @Setter private List<Cad_Auth_Usuarios> authorizathions = new ArrayList<>();
	
	public Cad_SisPC_Modulos(Integer moduloId, String modulo, String nivel) {
		super();
		this.moduloId = moduloId;
		this.modulo = modulo;
		this.nivel = nivel;
	}
	
}
