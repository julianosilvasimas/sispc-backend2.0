package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class IndicadorsNomesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Integer indicadorId;
	@Getter @Setter private String indicador;


	
	public IndicadorsNomesDTO(Cad_Ind_Indicadores obj) {
		super();
		this.indicadorId = obj.getIndicadorId();
		this.indicador = obj.getIndicador();
	}
}
