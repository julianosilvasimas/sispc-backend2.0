package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Base_GSSOnLine_ServicosTMA;

@Repository
public interface ServicosGSSOnLineRepository extends JpaRepository<Base_GSSOnLine_ServicosTMA, Integer>{

}
