package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_Projetos_Financeiros;

@Repository
public interface ProjFinanceirosRepository extends JpaRepository<Cad_Projetos_Financeiros, Integer> {

}
