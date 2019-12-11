package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_SisPC_Empresas;

@Repository
public interface EmpresasRepository extends JpaRepository<Cad_SisPC_Empresas, Integer>{

}
