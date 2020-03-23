package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Apprpa_Energia_Cenariounidades;


@Repository
public interface CenariosUnidadesRepository extends JpaRepository<Apprpa_Energia_Cenariounidades, Integer>{

}
