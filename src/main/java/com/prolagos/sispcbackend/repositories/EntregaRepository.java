package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Base_GSS_EndEntregas;


@Repository
public interface EntregaRepository extends JpaRepository<Base_GSS_EndEntregas, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Base_GSS_EndEntregas obj WHERE obj.matricula = :matricula ") 
	public List<Base_GSS_EndEntregas> findEndEntrega(@Param("matricula") Integer matricula);
	
}
