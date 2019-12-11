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

import com.prolagos.sispcbackend.domain.Cad_SisPC_Perfis;
import com.prolagos.sispcbackend.repositories.PerfisRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class PerfisService {
	
	@Autowired
	private PerfisRepository repo;

	public List<Cad_SisPC_Perfis> findAll() {
		return repo.findAll();
	}

	public Page<Cad_SisPC_Perfis> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_SisPC_Perfis find(Integer id) {
		Optional<Cad_SisPC_Perfis> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		Cad_SisPC_Perfis.class.getName(), null));
	}
	
	public Cad_SisPC_Perfis insert(Cad_SisPC_Perfis obj) {
		obj.setPerfilId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_SisPC_Perfis update(Cad_SisPC_Perfis obj) {
		find(obj.getPerfilId());
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
