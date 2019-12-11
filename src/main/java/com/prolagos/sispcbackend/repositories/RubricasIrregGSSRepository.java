package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Base_GSS_IrregRubricas;

@Repository
public interface RubricasIrregGSSRepository extends JpaRepository<Base_GSS_IrregRubricas, Integer>{

}
