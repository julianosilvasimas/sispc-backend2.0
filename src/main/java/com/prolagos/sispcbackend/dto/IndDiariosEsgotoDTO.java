package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
public class IndDiariosEsgotoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Integer indicadordiario;

	@Column(name = "datas", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String datas;
	@Getter @Setter private Double valor;
	@Getter @Setter private String usuario;
	
}
