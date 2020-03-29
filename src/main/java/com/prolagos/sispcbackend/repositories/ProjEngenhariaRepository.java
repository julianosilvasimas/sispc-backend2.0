package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_Projetos_Engenharia;
import com.prolagos.sispcbackend.domain.Cad_Projetos_Regulatorio;

@Repository
public interface ProjEngenhariaRepository extends JpaRepository<Cad_Projetos_Engenharia, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cad_Projetos_Engenharia obj WHERE obj.projetoId.projetoId = :id ORDER BY obj.engenhariaId")
	public List<Cad_Projetos_Engenharia> engPorProjeto(@Param("id") Integer id);

}
