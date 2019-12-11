package com.prolagos.sispcbackend.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.procedures.ListaIndicadores;

@Repository
public class ListaIndicadoresDAO {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ListaIndicadores> findAllBygrafico(String ref, Integer ind){
			return em.createNamedStoredProcedureQuery("procedure1").setParameter("referencia", ref).setParameter("indicador", ind).getResultList();
		}

}
