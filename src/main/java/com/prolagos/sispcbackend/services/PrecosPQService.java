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

import com.prolagos.sispcbackend.domain.Cad_Ind_Prodquimico;
import com.prolagos.sispcbackend.repositories.PrecosPQRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class PrecosPQService {
	
	@Autowired
	private PrecosPQRepository repo;

	public List<Cad_Ind_Prodquimico> findAll() {
		return repo.findAll();
	}

	public Page<Cad_Ind_Prodquimico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Ind_Prodquimico find(Integer id) {
		Optional<Cad_Ind_Prodquimico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Ind_Prodquimico.class.getName(), null));
	}
	
	public Cad_Ind_Prodquimico insert(Cad_Ind_Prodquimico obj) {
		obj.setProdutoId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Ind_Prodquimico update(Cad_Ind_Prodquimico obj) {
		find(obj.getProdutoId());
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
