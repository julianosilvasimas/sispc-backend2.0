package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.AppWeb_ProcessAdm_DelibFraudes;

@Repository
public interface GestaoDeliberacaoRepository extends JpaRepository<AppWeb_ProcessAdm_DelibFraudes, String>{

	//SELECT * FROM app_gestao_deliberacao ORDER BY processo DESC LIMIT 1
		@Transactional(readOnly=true)
		@Query("SELECT obj FROM AppWeb_ProcessAdm_DelibFraudes obj ORDER BY  obj.dataJulgado DESC") 
		public List<AppWeb_ProcessAdm_DelibFraudes> ultDelib();
		
		@Transactional(readOnly=true)
		@Query("SELECT obj FROM AppWeb_ProcessAdm_DelibFraudes obj WHERE obj.idIrregularidade = :idIrreg")
		public List<AppWeb_ProcessAdm_DelibFraudes> findIrreg(@Param("idIrreg") Integer idIrreg);
	
}
