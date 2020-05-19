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

import com.prolagos.sispcbackend.domain.Cad_Projetos_FinanceiroAnos;
import com.prolagos.sispcbackend.repositories.ProjFinanAnosRepository;
import com.prolagos.sispcbackend.repositories.ProjFinanceirosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjFinanceiroAnosService {
	
	@Autowired
	private ProjFinanAnosRepository repo;
	
	@Autowired
	private ProjFinanceirosRepository repo2;
	
	public List<Cad_Projetos_FinanceiroAnos> findAll() {
		return repo.findAll();
	}
	
	public List<Cad_Projetos_FinanceiroAnos> findByCapex(Integer capexId) {
		return repo.anoPorProjeto(capexId);
	}

	public Page<Cad_Projetos_FinanceiroAnos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_FinanceiroAnos find(Integer id) {
		Optional<Cad_Projetos_FinanceiroAnos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_FinanceiroAnos.class.getName(), null));
	}
	
	public Cad_Projetos_FinanceiroAnos insert(Cad_Projetos_FinanceiroAnos obj) {
		obj.setCapexanoId(null);  //Utilizado em Entidade Com auto incremento
		
		obj.setCapex(repo2.findById(obj.getCapex().getCapexId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!", null)));
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_FinanceiroAnos update(Cad_Projetos_FinanceiroAnos obj) {
		find(obj.getCapexanoId());
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Preço que possui relacionamentos");
		}
	}

}
