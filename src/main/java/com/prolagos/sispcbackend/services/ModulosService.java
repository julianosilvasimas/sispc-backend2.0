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

import com.prolagos.sispcbackend.domain.Cad_SisPC_Modulos;
import com.prolagos.sispcbackend.repositories.ModulosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ModulosService {
	
	@Autowired
	private ModulosRepository repo;

	public List<Cad_SisPC_Modulos> findAll() {
		return repo.findAll();
	}

	public Page<Cad_SisPC_Modulos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_SisPC_Modulos find(Integer id) {
		Optional<Cad_SisPC_Modulos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		Cad_SisPC_Modulos.class.getName(), null));
	}
	
	public Cad_SisPC_Modulos insert(Cad_SisPC_Modulos obj) {
		obj.setModuloId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_SisPC_Modulos update(Cad_SisPC_Modulos obj) {
		find(obj.getModuloId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um objeto que possui relacionamentos");
		}
	}

}
