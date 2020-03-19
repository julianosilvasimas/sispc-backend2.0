package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Apprpa_Energia_GestalLog;

@Repository
public interface AppRpaGestalLogRepository extends JpaRepository<Apprpa_Energia_GestalLog, Integer>{


	
}
