package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class EsgotoPreenchimentosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private Integer id;
	@Getter @Setter private Apprpa_Esgoto_Indicadores Indicador;
	@Getter @Setter private Apprpa_Esgoto_Unidades Unidade;
	@Getter @Setter private String dataDaImportacao;
	@Getter @Setter private String DataIndicador;
	@Getter @Setter private String Usuario;
	@Getter @Setter private Double Valor;
	@Getter @Setter private Integer Aprovado;
	
	public EsgotoPreenchimentosDTO(Apprpa_Esgoto_Preenchimentos obj){
		id = obj.getId();
		Indicador = obj.getIndicador();
		dataDaImportacao = obj.getDataDaImportacao();
		DataIndicador = obj.getDataIndicador();
		Usuario = obj.getUsuario();
		Valor = obj.getValor();
		Aprovado = obj.getAprovado();
		Unidade = obj.getUnidade();
		Unidade.setIndicadores(null);
	}
}

