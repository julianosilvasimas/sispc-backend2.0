package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Classificacoes;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;


@Repository
public interface ApprpaEsgotoIndicadoresRepository extends JpaRepository<Apprpa_Esgoto_Indicadores, Integer>{
	
	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Indicadores obj "
    		+ "WHERE obj.Classificacao = :clas "
		)
	List<Apprpa_Esgoto_Indicadores> findByClass(@Param("clas") final Apprpa_Esgoto_Classificacoes clas);
	
	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Indicadores obj "
    		+ "INNER JOIN obj.Unidades cad "
    		+ "WHERE cad.id = :unidade "
		)
	List<Apprpa_Esgoto_Indicadores> findByUnidade(@Param("unidade") final Integer unidade);
}
