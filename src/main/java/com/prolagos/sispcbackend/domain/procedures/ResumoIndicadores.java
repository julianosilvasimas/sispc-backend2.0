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
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name ="procedure2", procedureName="Indicadores_Diarios_Resumo", 
parameters= {
		@StoredProcedureParameter(mode=ParameterMode.IN, name="referencia",type= String.class),
		@StoredProcedureParameter(mode=ParameterMode.IN, name="indicador",type= Integer.class)
})})
@Table(name="procedureJPA_Resumo_indicadores")
public class ResumoIndicadores {
	
	@Id
	@Getter @Setter private String ind;
	@Getter @Setter private String col01;
	@Getter @Setter private String col02;
	@Getter @Setter private String col03;
	@Getter @Setter private String col04;
	@Getter @Setter private String col05;
	@Getter @Setter private String col06;
	@Getter @Setter private String col07;
	@Getter @Setter private String col08;
	@Getter @Setter private String col09;
	@Getter @Setter private String col10;
	@Getter @Setter private String col11;
	@Getter @Setter private String col12;

}
