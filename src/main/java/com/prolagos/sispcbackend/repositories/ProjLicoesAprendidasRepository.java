package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_Projetos_Licoesaprendidas;

@Repository
public interface ProjLicoesAprendidasRepository extends JpaRepository<Cad_Projetos_Licoesaprendidas, Integer> {

}
