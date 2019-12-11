package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Cad_SisPC_Usuarios, Integer> {
	
	@Transactional(readOnly=true)
	Cad_SisPC_Usuarios findByEmail(String email);

}
