package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.prolagos.sispcbackend.domain.Apprpa_Energia_cadCenarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CenariosEnergiaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Integer id;

	@Column(name = "ImportadoParaIndicadores", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String ImportadoParaIndicadores;
	
	@Column(name = "DataReferencia", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String DataReferencia;
	
	@Getter @Setter private Integer Classificacao;
	@Getter @Setter private String NomeDoCenario;
	@Getter @Setter private String Usuario;
	@Getter @Setter private Double Tarifa;
	@Getter @Setter private Double Aumento;
	
	public CenariosEnergiaDTO(Apprpa_Energia_cadCenarios obj) {
		super();
		this.id = obj.getId();
		ImportadoParaIndicadores = obj.getImportadoParaIndicadores();
		DataReferencia = obj.getDataReferencia();
		NomeDoCenario = obj.getNomeDoCenario();
		Usuario = obj.getUsuario();
		Tarifa = obj.getTarifa();
		Aumento = obj.getAumento();
		Classificacao = obj.getClassificacao();
	}
}
