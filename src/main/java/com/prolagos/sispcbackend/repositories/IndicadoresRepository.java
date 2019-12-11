package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;

@Repository 
public interface IndicadoresRepository extends JpaRepository<AppWeb_Ind_ExeIndicadores, Integer>{

	
}
