package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;

@Repository
public interface CadindicadoresRepository extends JpaRepository<Cad_Ind_Indicadores, Integer>{

}
