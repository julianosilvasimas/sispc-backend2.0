package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Classificacoes;


@Repository
public interface ApprpaEsgotoClassificacoesRepository extends JpaRepository<Apprpa_Esgoto_Classificacoes, Integer>{
}
