package com.prolagos.sispcbackend.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Unidades;
import com.prolagos.sispcbackend.domain.procedures.ListaIndicadores;
import com.prolagos.sispcbackend.domain.procedures.ResumoIndicadores;

@Repository
public class ListaIndicadoresDAO {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ListaIndicadores> findAllBygrafico(String ref, Integer ind){
			return em.createNamedStoredProcedureQuery("procedure1").setParameter("referencia", ref).setParameter("indicador", ind).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ResumoIndicadores> findAllBygraficoResumo(String ref, Integer ind){
			return em.createNamedStoredProcedureQuery("procedure2").setParameter("referencia", ref).setParameter("indicador", ind).getResultList();
	}
	
	
	public boolean insert(String acao, String analise, Integer  atendente, Integer atendimento, Integer ciclo, String colaborador, String comentario, LocalDate dataindicador, LocalDate datareferencia,
			Double dentroprazo, Double dentroprazoreg, Double foraprazo, Double foraprazoreg, Double forecast, Double maximo, Double meta, Double minimo, Double orcado , Double pecld, Integer periodicidade,
			Double previsao, Double realizado, Cad_Ind_Indicadores indicadorId, Cad_SisPC_Unidades undcodigo, Double  realizadokg ) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("inputIndicadores");
		
		query.registerStoredProcedureParameter("entradaacao", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaanalise", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaatendente", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaatendimento", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaciclo", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradacolaborador", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradacomentario", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradadataindicador", LocalDate.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradadatareferencia", LocalDate.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradadentroprazo", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradadentroprazoreg", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaforaprazo", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaforaprazoreg", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaforecast", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradamaximo", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradameta", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaminimo", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaorcado", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradapecld", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaperiodicidade", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradaprevisao", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradarealizado", Double.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradafkIndicador", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradafkempresa", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("entradarealizadokg", Double.class, ParameterMode.IN);
		
		
		query.setParameter("entradaacao",acao)
			.setParameter("entradaanalise", analise)
			.setParameter("entradaatendente", atendente)
			.setParameter("entradaatendimento", atendimento)
			.setParameter("entradaciclo", ciclo)
			.setParameter("entradacolaborador", colaborador)
			.setParameter("entradacomentario", comentario)
			.setParameter("entradadataindicador", dataindicador)
			.setParameter("entradadatareferencia", datareferencia)
			.setParameter("entradadentroprazo", dentroprazo)
			.setParameter("entradadentroprazoreg", dentroprazoreg)
			.setParameter("entradaforaprazo", foraprazo)
			.setParameter("entradaforaprazoreg", foraprazoreg )
			.setParameter("entradaforecast", forecast)
			.setParameter("entradamaximo", maximo)
			.setParameter("entradameta", meta)
			.setParameter("entradaminimo", minimo)
			.setParameter("entradaorcado", orcado)
			.setParameter("entradapecld", pecld)
			.setParameter("entradaperiodicidade", 3)
			.setParameter("entradaprevisao", previsao)
			.setParameter("entradarealizado", realizado)
			.setParameter("entradafkIndicador", indicadorId.getIndicadorId())
			.setParameter("entradafkempresa", undcodigo.getUnidadeId())
			.setParameter("entradarealizadokg", realizadokg );
		
		return query.execute();
	}


	

}
