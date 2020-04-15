package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;


@Repository
public interface ApprpaEsgotoUnidadesRepository extends JpaRepository<Apprpa_Esgoto_Unidades, Integer>{


	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj "
    		+ "FROM Apprpa_Esgoto_Unidades obj "
    		+ "INNER JOIN obj.Operadores cad "
    		+ "WHERE :usuario IN cad"
		)
	List<Apprpa_Esgoto_Unidades> findUsuario(
			@Param("usuario") final Cad_SisPC_Usuarios usuario
			);
}
