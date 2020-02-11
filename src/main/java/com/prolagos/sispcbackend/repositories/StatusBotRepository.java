package com.prolagos.sispcbackend.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Rpa_Statusbots;
import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;


@Repository
public interface StatusBotRepository extends JpaRepository<Apprpa_Rpa_Statusbots, Integer>{

	
	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Apprpa_Rpa_Statusbots obj where obj.Bot = :bot")
    Optional<Apprpa_Rpa_Statusbots> findByBot(@Param("bot") final Integer bot);
	
}
