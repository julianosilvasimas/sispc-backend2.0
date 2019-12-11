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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "empresaId")
public class Cad_SisPC_Empresas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer empresaId;
	@Getter @Setter private String empresa;
	@Getter @Setter private Integer cnpj;
	@Getter @Setter private String tag;
	
	@JsonIgnore
	@OneToMany(mappedBy="empresaId")
	@Getter @Setter private List<Cad_SisPC_Regionais> regionais= new ArrayList<>();

}
