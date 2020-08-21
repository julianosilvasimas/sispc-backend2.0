package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_SisPC_PartesInteressadas;

@Repository
public interface PartesInteressadasRepository extends JpaRepository<Cad_SisPC_PartesInteressadas, Integer> {

}
