package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Apprpa_Energia_Gestal;
import com.prolagos.sispcbackend.domain.Cad_Energia_Gestal;

@Repository
public interface AppRpaGestalRepository extends JpaRepository<Apprpa_Energia_Gestal, Integer>{


	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Apprpa_Energia_Gestal obj WHERE obj.Aprovacao IS NULL")
	List<Apprpa_Energia_Gestal> paraAprovar();
	
	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Apprpa_Energia_Gestal obj WHERE obj.Aprovacao IS NULL AND obj.Unidade = :equip")
	List<Apprpa_Energia_Gestal> paraAprovar2(@Param("equip") final Cad_Energia_Gestal equip);
	
	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Apprpa_Energia_Gestal obj INNER JOIN obj.Unidade cad  WHERE obj.DataIndicador >= :data1 AND obj.DataIndicador <= :data2 "
    		+ "AND cad.Classificacao = :Classif ORDER BY obj.Unidade")
	List<Apprpa_Energia_Gestal> paraData(@Param("data1") final String data1,@Param("data2") final String data2,@Param("Classif") final Integer Classif);
	

	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Apprpa_Energia_Gestal obj WHERE obj.Unidade = :equip AND obj.DataIndicador >= :data1 AND obj.DataIndicador <= :data2")
	Apprpa_Energia_Gestal pelaData(@Param("equip") final Cad_Energia_Gestal equip, @Param("data1") final String data1, @Param("data2") final String data2);
	
}
