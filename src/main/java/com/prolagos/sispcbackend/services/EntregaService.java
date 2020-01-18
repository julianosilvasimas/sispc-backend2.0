package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Base_GSS_EndEntregas;
import com.prolagos.sispcbackend.repositories.EntregaRepository;

@Service
public class EntregaService {
	@Autowired
	private EntregaRepository repo;
	
	public List<Base_GSS_EndEntregas> findAll() {
		return repo.findAll();
	}
	
	public Base_GSS_EndEntregas find(Integer matricula) {
		Optional<Base_GSS_EndEntregas> obj = repo.findById(matricula);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + matricula + ", Tipo: " + Base_GSS_EndEntregas.class.getName(), null));
	}
	
}