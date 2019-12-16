package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CadindicadoresRepository extends JpaRepository<Cad_Ind_Indicadores, Integer>{

	 @Transactional(readOnly = true)
	    @Query("SELECT obj FROM Cad_Ind_Indicadores obj WHERE obj.gerencia = :gerencia order by obj.ordem")
	    List<Cad_Ind_Indicadores> classIndicadores(@Param("gerencia") final Integer gerencia);
	
}
