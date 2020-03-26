package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;


@Repository
public interface ApprpaEsgotoRepository extends JpaRepository<Apprpa_Esgoto_Preenchimentos, Integer>{

	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Preenchimentos obj "
    		+ "WHERE obj.DataIndicador BETWEEN :de AND :ate "
    		+ "AND   obj.Unidade LIKE :Unidade "
    		+ "AND 	 obj.Indicador IN :clas "
    		+ "ORDER BY obj.Indicador, obj.DataIndicador"
		)
	List<Apprpa_Esgoto_Preenchimentos> consultaPorData(@Param("Unidade") final String Unidade, @Param("de") final String de, @Param("ate") final String ate, @Param("clas") final Object[] clas);
}
