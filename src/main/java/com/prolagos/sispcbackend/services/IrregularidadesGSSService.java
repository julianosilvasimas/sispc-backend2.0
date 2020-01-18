package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Base_GSS_Irregularidades;
import com.prolagos.sispcbackend.repositories.IrregularidadesGSSRepository;
import com.prolagos.sispcbackend.services.exceptions.ObjectNotFoundException;

@Service
public class IrregularidadesGSSService {

	@Autowired
	private IrregularidadesGSSRepository repo;

	public List<Base_GSS_Irregularidades> findAll() {
		return repo.findAll();
	}
	
	public Base_GSS_Irregularidades find(Integer idIrregularidade) {
		Optional<Base_GSS_Irregularidades> obj = repo.findById(idIrregularidade);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + idIrregularidade + ", Tipo: " + Base_GSS_Irregularidades.class.getName(), null));
	}
	
	public List<Base_GSS_Irregularidades> findIrregularidades(Integer contratoId) {
			return repo.findIrregularidades(contratoId);
	}
	
	public List<Base_GSS_Irregularidades> findTermo(String termo) {
		List<Base_GSS_Irregularidades> obj = repo.findTermo(termo);
		return obj;
	}
	
	public List<Base_GSS_Irregularidades> findTermoStatus(String termo, String status ) {
		List<Base_GSS_Irregularidades> obj = repo.findTermoStatus(termo, status);
		return obj;
	}
	
}
