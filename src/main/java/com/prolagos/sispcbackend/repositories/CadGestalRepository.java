package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_Energia_Gestal;

@Repository
public interface CadGestalRepository extends JpaRepository<Cad_Energia_Gestal, Integer>{


}
