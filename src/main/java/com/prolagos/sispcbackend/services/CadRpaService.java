package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.cad_rpa_robos;
import com.prolagos.sispcbackend.repositories.CadRpaRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class CadRpaService {

	@Autowired
	private CadRpaRepository repo;

	public List<cad_rpa_robos> findAll() {
		return repo.findAll();
	}
	

	public Page<cad_rpa_robos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public cad_rpa_robos find(Integer id) {
		Optional<cad_rpa_robos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		cad_rpa_robos.class.getName(), null));
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Objeto que possui relacionamentos");
		}
	}
	
}
