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

import com.prolagos.sispcbackend.domain.Cad_SisPC_Projetos;
import com.prolagos.sispcbackend.repositories.ProjetosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjetosService {
	
	@Autowired
	private ProjetosRepository repo;

	public List<Cad_SisPC_Projetos> findAll() {
		return repo.findAll();
	}

	public Page<Cad_SisPC_Projetos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_SisPC_Projetos find(Integer id) {
		Optional<Cad_SisPC_Projetos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_SisPC_Projetos.class.getName(), null));
	}
	
	public Cad_SisPC_Projetos insert(Cad_SisPC_Projetos obj) {
		obj.setProjetoId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_SisPC_Projetos update(Cad_SisPC_Projetos obj) {
		find(obj.getProjetoId());
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
