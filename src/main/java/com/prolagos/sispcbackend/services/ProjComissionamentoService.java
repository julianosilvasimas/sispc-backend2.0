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

import com.prolagos.sispcbackend.domain.Cad_Projetos_Comissionamento;
import com.prolagos.sispcbackend.repositories.ProjComissionamentoRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjComissionamentoService {
	@Autowired
	private ProjComissionamentoRepository repo;
	
	public List<Cad_Projetos_Comissionamento> findAll() {
		return repo.findAll();
	}

	public Page<Cad_Projetos_Comissionamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_Comissionamento find(Integer id) {
		Optional<Cad_Projetos_Comissionamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_Comissionamento.class.getName(), null));
	}
	
	public Cad_Projetos_Comissionamento insert(Cad_Projetos_Comissionamento obj) {
		//obj.setComissionamentoId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_Comissionamento update(Cad_Projetos_Comissionamento obj) {
		find(obj.getComissionamentoId());
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
