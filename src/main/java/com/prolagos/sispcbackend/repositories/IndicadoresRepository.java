package com.prolagos.sispcbackend.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;

@Repository 
public interface IndicadoresRepository extends JpaRepository<AppWeb_Ind_ExeIndicadores, Integer>{

	@Transactional(readOnly = true)
    @Query("SELECT obj FROM AppWeb_Ind_ExeIndicadores obj INNER JOIN obj.indicadorId cad WHERE cad IN :indicador AND obj.dataindicador = :data")
    Optional<AppWeb_Ind_ExeIndicadores> findIndDiario(@Param("indicador") final List<Cad_Ind_Indicadores> indicador, @Param("data") final LocalDate data);
	
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM AppWeb_Ind_ExeIndicadores obj INNER JOIN obj.indicadorId cad WHERE cad IN :indicador AND obj.datareferencia = :data")
    List<AppWeb_Ind_ExeIndicadores> findIndDiariosPorMes(@Param("indicador") final List<Cad_Ind_Indicadores> indicador, @Param("data") final LocalDate data);
    
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM AppWeb_Ind_ExeIndicadores obj INNER JOIN obj.indicadorId cad WHERE cad.indicadorId = :indicador AND obj.dataindicador = :data")
    AppWeb_Ind_ExeIndicadores findByDataInd(@Param("indicador") final Integer indicador, @Param("data") final LocalDate data);
    
}
