package com.prolagos.sispcbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;


@Repository
public interface AgendamentosRepository extends JpaRepository<Appweb_Transporte_Agendamentos, Integer>{


	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Transporte_Agendamentos obj WHERE obj.Aprovacao IS NULL")
	List<Appweb_Transporte_Agendamentos> paraAprovar();

	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Transporte_Agendamentos obj WHERE obj.Aprovacao IS NOT NULL")
	List<Appweb_Transporte_Agendamentos> Aprovados();
	
	@Transactional(readOnly = true)
    @Query( "SELECT "
    		+ "obj2 "
    		+ "FROM Appweb_Transporte_Agendamentos obj2 "
    		+ "WHERE obj2.Placa IS NOT NULL AND :de  BETWEEN obj2.Agendadode AND obj2.Agendadoate "
    		+ "OR    obj2.Placa IS NOT NULL AND :ate BETWEEN obj2.Agendadode AND obj2.Agendadoate "
    		+ "OR(   obj2.Placa IS NOT NULL AND :de <= obj2.Agendadode AND :ate >= obj2.Agendadoate)"
		)
	List<Appweb_Transporte_Agendamentos> Disponiveis(@Param("de") final String de, @Param("ate") final String ate);
}
