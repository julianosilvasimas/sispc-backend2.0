package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Base_GSS_Irregularidades;

@Repository
public interface IrregularidadesGSSRepository extends JpaRepository<Base_GSS_Irregularidades, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Base_GSS_Irregularidades obj WHERE obj.contrato.id = :contratoId ") 
	public List<Base_GSS_Irregularidades> findIrregularidades(@Param("contratoId") Integer contratoId);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Base_GSS_Irregularidades obj WHERE obj.num_termo_ocorrencia = :termo") 
	public List<Base_GSS_Irregularidades> findTermo(@Param("termo") String termo);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Base_GSS_Irregularidades obj WHERE obj.num_termo_ocorrencia = :termo and obj.sit_notificacao Like %:status%") 
	public List<Base_GSS_Irregularidades> findTermoStatus(@Param("termo") String termo, @Param("status") String status);
	
}
