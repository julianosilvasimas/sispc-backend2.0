package com.prolagos.sispcbackend.domain.procedures;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name ="procedure2", procedureName="Indicadores_Diarios_Resumo2", 
parameters= {
		@StoredProcedureParameter(mode=ParameterMode.IN, name="referencia",type= String.class),
		@StoredProcedureParameter(mode=ParameterMode.IN, name="indicador",type= Integer.class)
})})
@Table(name="procedureJPA_lista_indicadores")
public class ResumoIndicadores {
	
	@Id
	@Getter @Setter private String ind;
	@Getter @Setter private String dia01;
	@Getter @Setter private String dia02;
	@Getter @Setter private String dia03;
	@Getter @Setter private String dia04;
	@Getter @Setter private String dia05;
	@Getter @Setter private String dia06;
	@Getter @Setter private String dia07;
	@Getter @Setter private String dia08;
	@Getter @Setter private String dia09;
	@Getter @Setter private String dia10;
	@Getter @Setter private String dia11;
	@Getter @Setter private String dia12;
	@Getter @Setter private String dia13;
	@Getter @Setter private String dia14;
	@Getter @Setter private String dia15;
	@Getter @Setter private String dia16;
	@Getter @Setter private String dia17;
	@Getter @Setter private String dia18;
	@Getter @Setter private String dia19;
	@Getter @Setter private String dia20;
	@Getter @Setter private String dia21;
	@Getter @Setter private String dia22;
	@Getter @Setter private String dia23;
	@Getter @Setter private String dia24;
	@Getter @Setter private String dia25;
	@Getter @Setter private String dia26;
	@Getter @Setter private String dia27;
	@Getter @Setter private String dia28;
	@Getter @Setter private String dia29;
	@Getter @Setter private String dia30;
	@Getter @Setter private String dia31;

}
