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

import com.prolagos.sispcbackend.domain.Cad_Energia_Gestal;
import com.prolagos.sispcbackend.repositories.CadGestalRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class CadGestalService {

	@Autowired
	private CadGestalRepository repo;

	public List<Cad_Energia_Gestal> findAll() {
		return repo.findAll();
	}
	

	public Page<Cad_Energia_Gestal> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Energia_Gestal find(Integer id) {
		Optional<Cad_Energia_Gestal> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Energia_Gestal.class.getName(), null));
	}
	
	public Cad_Energia_Gestal insert(Cad_Energia_Gestal obj) {
		obj.setIdEquipamento(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Energia_Gestal update(Cad_Energia_Gestal obj) {
		find(obj.getIdEquipamento());
		return repo.save(obj);
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
