package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.prolagos.sispcbackend.domain.Cad_Ind_Graficos;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CadindicadoresGraficosRepository extends JpaRepository<Cad_Ind_Graficos, Integer>{


}
