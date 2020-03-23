package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Apprpa_Energia_Cenario;


@Repository
public interface CenariosEnergiaRepository extends JpaRepository<Apprpa_Energia_Cenario, Integer>{

}
