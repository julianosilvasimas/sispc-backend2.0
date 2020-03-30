package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_Projetos_FinanceiroAnos;

public interface ProjFinanAnosRepository extends JpaRepository<Cad_Projetos_FinanceiroAnos, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cad_Projetos_FinanceiroAnos obj WHERE obj.capexId = :id ORDER BY obj.inicioprojcognos")
	public List<Cad_Projetos_FinanceiroAnos> anoPorProjeto(@Param("id") Integer id);

}
