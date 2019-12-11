package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_Projetos_Contratacao;

@Repository
public interface ProjContratacaoRepository extends JpaRepository<Cad_Projetos_Contratacao, Integer> {

}
