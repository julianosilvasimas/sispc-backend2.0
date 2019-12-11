package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.AppWeb_ProcessAdm_DelibFraudes;

@Repository
public interface DelibFraudesRepository extends JpaRepository<AppWeb_ProcessAdm_DelibFraudes, Integer>{

}
