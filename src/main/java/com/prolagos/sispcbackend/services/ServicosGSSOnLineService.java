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

import com.prolagos.sispcbackend.domain.Base_GSSOnLine_ServicosTMA;
import com.prolagos.sispcbackend.repositories.ServicosGSSOnLineRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ServicosGSSOnLineService {
	@Autowired
	private ServicosGSSOnLineRepository repo;

	public List<Base_GSSOnLine_ServicosTMA> findAll() {
		return repo.findAll();
	}

	public Page<Base_GSSOnLine_ServicosTMA> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Base_GSSOnLine_ServicosTMA find(Integer id) {
		Optional<Base_GSSOnLine_ServicosTMA> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		Base_GSSOnLine_ServicosTMA.class.getName(), null));
	}
	
	public Base_GSSOnLine_ServicosTMA insert(Base_GSSOnLine_ServicosTMA obj) {
		obj = repo.save(obj);
		return obj;
	}
	
	public Base_GSSOnLine_ServicosTMA update(Base_GSSOnLine_ServicosTMA obj) {
		find(obj.getIdosp());
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
