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

import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;
import com.prolagos.sispcbackend.repositories.CadindicadoresRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class CadindicadoresService {

	@Autowired
	private CadindicadoresRepository repo;

	public List<Cad_Ind_Indicadores> findAll() {
		return repo.findAll();
	}
	
	public List<Cad_Ind_Indicadores> classIndicadores(final Integer gerencia) {
	    return (List<Cad_Ind_Indicadores>)this.repo.classIndicadores(gerencia);
	}

	public Page<Cad_Ind_Indicadores> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Ind_Indicadores find(Integer id) {
		Optional<Cad_Ind_Indicadores> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Ind_Indicadores.class.getName(), null));
	}
	
	public Cad_Ind_Indicadores insert(Cad_Ind_Indicadores obj) {
		obj.setIndicadorId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Ind_Indicadores update(Cad_Ind_Indicadores obj) {
		find(obj.getIndicadorId());
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
