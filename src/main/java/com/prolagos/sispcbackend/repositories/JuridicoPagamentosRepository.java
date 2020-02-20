package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Appweb_Juridico_pagamentos;


@Repository
public interface JuridicoPagamentosRepository extends JpaRepository<Appweb_Juridico_pagamentos, Integer>{
	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Juridico_pagamentos obj WHERE obj.EnviadoParaAprovacao = 0 order by obj.DataCadastro asc")
    List<Appweb_Juridico_pagamentos> enviarParaAprovacao();

	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Juridico_pagamentos obj WHERE "
    		+ "(obj.EnviadoParaAprovacao = 3 AND (obj.Aprovacao1 IS NULL OR obj.Aprovacao2 IS NULL OR obj.Aprovacao3 IS NULL)) OR "
    		+ "(obj.EnviadoParaAprovacao = 2 AND (obj.Aprovacao1 IS NULL OR obj.Aprovacao2 IS NULL)) OR "
    		+ "(obj.EnviadoParaAprovacao = 1 AND (obj.Aprovacao1 IS NULL))")
    List<Appweb_Juridico_pagamentos> emAprovacao();
    

	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Juridico_pagamentos obj WHERE "
    		+ "(obj.EnviadoParaAprovacao <= 3 AND (obj.Aprovacao1 IS NULL))")
    List<Appweb_Juridico_pagamentos> emAprovacaoNivel1();
    

	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Juridico_pagamentos obj WHERE "
    		+ "(obj.EnviadoParaAprovacao = 3 AND (obj.Aprovacao3 IS NULL))")
    List<Appweb_Juridico_pagamentos> emAprovacaoNivel3();
    

	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Appweb_Juridico_pagamentos obj WHERE obj.CentroDeCusto IN (:centroDeCusto) AND ("
    		+ "(obj.EnviadoParaAprovacao = 3 AND (obj.Aprovacao2 IS NULL)) OR "
    		+ "(obj.EnviadoParaAprovacao = 2 AND (obj.Aprovacao2 IS NULL))"
    		+ ")")
    List<Appweb_Juridico_pagamentos> emAprovacaoNivel2(@Param("centroDeCusto") final List<String> centroDeCusto);
}
