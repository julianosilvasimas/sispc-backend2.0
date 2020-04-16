package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Ocorrencias;


@Repository
public interface ApprpaEsgotoOcorrenciasRepository extends JpaRepository<Apprpa_Esgoto_Ocorrencias, Integer>{			

	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Ocorrencias obj "
    		+ "ORDER BY obj.id DESC"
		)
	List<Apprpa_Esgoto_Ocorrencias> findLimit(Pageable pageable);
}
