package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_Projetos_ComprovacaoArquivos;

@Repository
public interface ProjComprovacaoArquivosRepository extends JpaRepository<Cad_Projetos_ComprovacaoArquivos, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cad_Projetos_ComprovacaoArquivos obj WHERE obj.comprovacaoId.comprovacaoId = :id" )
	public List<Cad_Projetos_ComprovacaoArquivos> arquivoPorProjeto(@Param("id") Integer id);

}
