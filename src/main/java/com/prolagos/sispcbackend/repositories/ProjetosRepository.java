package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_Projetos_Regulatorio;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Projetos;

@Repository
public interface ProjetosRepository extends JpaRepository<Cad_SisPC_Projetos, Integer> {
	
		@Transactional(readOnly=true)
		@Query("SELECT obj FROM Cad_SisPC_Projetos obj LEFT OUTER JOIN obj.regulatorio cad "
				+ "WHERE cad IN :regula AND obj.projetoId = :projetoId AND obj.projetoId = cad.projetoId")
		public Cad_SisPC_Projetos findRegulatorios(@Param("regula") final List<Cad_Projetos_Regulatorio> regula,@Param("projetoId") Integer projetoId);

}
