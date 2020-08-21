package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_Projetos_DeliberacoesRegulatorios;

@Repository
public interface ProjDelibRegulatorioRepository extends JpaRepository<Cad_Projetos_DeliberacoesRegulatorios, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cad_Projetos_DeliberacoesRegulatorios obj WHERE obj.regulatorio.regulatorioId = :id" )
	public List<Cad_Projetos_DeliberacoesRegulatorios> delibPorRev(@Param("id") Integer id);
}
