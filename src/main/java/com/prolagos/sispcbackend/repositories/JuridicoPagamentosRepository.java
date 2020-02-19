package com.prolagos.sispcbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Appweb_Juridico_pagamentos;
import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;


@Repository
public interface JuridicoPagamentosRepository extends JpaRepository<Appweb_Juridico_pagamentos, Integer>{
	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Juridico_pagamentos obj WHERE obj.EnviadoParaAprovacao = 0 order by obj.DataCadastro asc")
    List<Appweb_Juridico_pagamentos> enviarParaAprovacao();
	
}
