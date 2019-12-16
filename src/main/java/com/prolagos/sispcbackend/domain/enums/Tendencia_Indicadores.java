package com.prolagos.sispcbackend.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum Tendencia_Indicadores {

	MELHORPOSITIVO(1, "MelhorPositivo"), 
    MELHORNEGATIVO(2, "MelhorNegativo"), 
    MELHORENTREFAIXAS(3, "MelhorEntre");
	
	@Getter @Setter private int cod;
	@Getter @Setter private String descricao;
	
	 private Tendencia_Indicadores(final int cod, final String descricao) {
	        this.cod = cod;
	        this.descricao = descricao;
	    }
	
	public static Tendencia_Indicadores toEnum(final Integer cod) {
        if (cod == null) {
            return null;
        }
        for (final Tendencia_Indicadores x : values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inv\u00e1lido: " + cod);
    }
	
}
