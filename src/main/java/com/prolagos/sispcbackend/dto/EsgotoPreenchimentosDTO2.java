package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
public class EsgotoPreenchimentosDTO2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Integer id;
	@Getter @Setter private Integer indicador;
	@Getter @Setter private Integer unidade;
	@Getter @Setter private String data;
	@Getter @Setter private Double valor;
	@Getter @Setter private String usuario;
	
	
}

