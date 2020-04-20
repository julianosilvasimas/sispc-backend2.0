package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;


@Repository
public interface ApprpaEsgotoRepository extends JpaRepository<Apprpa_Esgoto_Preenchimentos, Integer>{

	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Preenchimentos obj "
    		+ "WHERE obj.DataIndicador BETWEEN :de AND :ate "
    		+ "AND   obj.Unidade LIKE :Unidade "
    		+ "AND   obj.Usuario LIKE :usuario "
    		+ "ORDER BY obj.Indicador, obj.DataIndicador"
		)
	List<Apprpa_Esgoto_Preenchimentos> consultaPorDataUsuario(@Param("Unidade") final Apprpa_Esgoto_Unidades Unidade, @Param("de") final String de, @Param("ate") final String ate,	@Param("usuario") final String usuario);
	
	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Preenchimentos obj "
    		+ "WHERE obj.DataIndicador BETWEEN :de AND :ate "
    		+ "AND   obj.Unidade LIKE :Unidade "
    		+ "ORDER BY obj.Indicador, obj.DataIndicador"
		)
	List<Apprpa_Esgoto_Preenchimentos> consultaPorData( @Param("Unidade") final Apprpa_Esgoto_Unidades Unidade, @Param("de") final String de, @Param("ate") final String ate);
	
	
	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Preenchimentos obj "
    		+ "INNER JOIN obj.Unidade cad "
    		+ "WHERE cad.id = :unidade "
    		+ "AND obj.Indicador = :indicador "
    		+ "ORDER BY obj.DataIndicador DESC"
		)
	List<Apprpa_Esgoto_Preenchimentos> findProdQuim(Pageable pageable, @Param("unidade") final Integer unidade, @Param("indicador") Apprpa_Esgoto_Indicadores indicador);
	


	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Preenchimentos obj "
    		+ "WHERE obj.Aprovado = 0 "
    		+ "ORDER BY obj.Indicador, obj.DataIndicador"
		)
	List<Apprpa_Esgoto_Preenchimentos> findByNaoAprovados();
	

	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Preenchimentos obj "
    		+ "WHERE   obj.Usuario LIKE :usuario "
    		+ "AND   obj.Aprovado = 0 "
    		+ "ORDER BY obj.Indicador, obj.DataIndicador"
		)
	List<Apprpa_Esgoto_Preenchimentos> findByNaoAprovadosUser(
			@Param("usuario") final String usuario
			);
			
}
