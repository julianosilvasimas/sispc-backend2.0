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

import com.prolagos.sispcbackend.domain.Apprpa_Rpa_Statusbots;
import com.prolagos.sispcbackend.repositories.CadindicadoresRepository;
import com.prolagos.sispcbackend.repositories.StatusBotRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class StatusBotsService {

	@Autowired
	private StatusBotRepository repo;

	public List<Apprpa_Rpa_Statusbots> findAll() {
		return repo.findAll();
	}
	
	

	public Page<Apprpa_Rpa_Statusbots> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Rpa_Statusbots find(Integer id) {
		Optional<Apprpa_Rpa_Statusbots> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Rpa_Statusbots.class.getName(), null));
	}

	public Apprpa_Rpa_Statusbots findByBot(Integer id) {
		Optional<Apprpa_Rpa_Statusbots> obj = repo.findByBot(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Rpa_Statusbots.class.getName(), null));
	}
	
	public Apprpa_Rpa_Statusbots insert(Apprpa_Rpa_Statusbots obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Apprpa_Rpa_Statusbots update(Apprpa_Rpa_Statusbots obj) {
		find(obj.getId());
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
