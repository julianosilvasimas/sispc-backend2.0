package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Apprpa_Transporte_Utilizacao;


@Repository
public interface ApprpaTransporteGolsat extends JpaRepository<Apprpa_Transporte_Utilizacao, Integer>{
	
}
