package com.prolagos.sispcbackend.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class EsgotoUnidadesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Integer id;
	@Getter @Setter private String dataDaCriacao;
	@Getter @Setter private String Unidade;
	@Getter @Setter private Double Vazao;
	@Getter @Setter private String TipoDeTratamento;
	@Getter @Setter private List<Cad_SisPC_Usuarios> Operadores;
	@Getter @Setter private List<Apprpa_Esgoto_Indicadores> Indicadores;
	
	
	public EsgotoUnidadesDTO(Apprpa_Esgoto_Unidades obj){
		id = obj.getId();
		dataDaCriacao = obj.getDataDaCriacao();
		Unidade = obj.getUnidade();
		Vazao = obj.getVazao();
		TipoDeTratamento = obj.getTipoDeTratamento();
	}
	public EsgotoUnidadesDTO(String Unidade, List<Cad_SisPC_Usuarios> Operadoress){
		this.Unidade = Unidade;
		this.Operadores = Operadoress;
	}
}

