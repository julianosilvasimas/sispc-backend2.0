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

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Ocorrencias;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoOcorrenciasRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ApprpaEsgotoOcorrenciasService {

	@Autowired
	private ApprpaEsgotoOcorrenciasRepository repo;

	public List<Apprpa_Esgoto_Ocorrencias> findAll() {
		return repo.findAll();
	}

	public Page<Apprpa_Esgoto_Ocorrencias> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Esgoto_Ocorrencias find(Integer id) {
		Optional<Apprpa_Esgoto_Ocorrencias> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		Apprpa_Esgoto_Ocorrencias.class.getName(), null));
	}
	
	public List<Apprpa_Esgoto_Ocorrencias> findLimit() {
		return repo.findLimit(new PageRequest(0, 25));
	}
	
	public Apprpa_Esgoto_Ocorrencias insert(Apprpa_Esgoto_Ocorrencias obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Apprpa_Esgoto_Ocorrencias update(Apprpa_Esgoto_Ocorrencias obj) {
		find(obj.getId());
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
