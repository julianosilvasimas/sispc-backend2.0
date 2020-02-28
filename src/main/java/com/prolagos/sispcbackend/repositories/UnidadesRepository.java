package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_SisPC_Gerencias;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Unidades;

@Repository
public interface UnidadesRepository extends JpaRepository<Cad_SisPC_Unidades, Integer>{

}
