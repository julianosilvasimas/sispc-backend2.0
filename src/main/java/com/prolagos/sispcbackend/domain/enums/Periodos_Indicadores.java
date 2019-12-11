package com.prolagos.sispcbackend.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Periodos_Indicadores {
	
	DIARIO(1, "Diário"),
	SEMANAL(2, "Semanal"),
	QUNZENAL(3, "Quinzenal"),
	MENSAL(4, "Diário");
	
	@Getter private int cod;
	@Getter private String descricao;
	
	
	public static Periodos_Indicadores toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Periodos_Indicadores x : Periodos_Indicadores.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
