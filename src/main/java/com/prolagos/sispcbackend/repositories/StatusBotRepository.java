package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Apprpa_Rpa_Statusbots;


@Repository
public interface StatusBotRepository extends JpaRepository<Apprpa_Rpa_Statusbots, Integer>, PagingAndSortingRepository<Apprpa_Rpa_Statusbots,Integer>{

	
	@Transactional(readOnly = true)
    @Query(""
    		+ "SELECT obj "
    		+ "FROM Apprpa_Rpa_Statusbots obj "
    		+ "INNER JOIN obj.Bot cad "
    		+ "WHERE cad.IdCad = :bot "
    		+ "ORDER BY obj.IdAppWeb DESC ")
    List<Apprpa_Rpa_Statusbots> findByBot(Pageable paging,@Param("bot") final Integer bot);

}
