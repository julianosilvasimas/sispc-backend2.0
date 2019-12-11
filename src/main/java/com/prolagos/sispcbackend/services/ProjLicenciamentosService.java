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

import com.prolagos.sispcbackend.domain.Cad_Projetos_Licenciamentos;
import com.prolagos.sispcbackend.repositories.ProjLicenciamentosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjLicenciamentosService {

	@Autowired
	private ProjLicenciamentosRepository repo;
	
	public List<Cad_Projetos_Licenciamentos> findAll() {
		return repo.findAll();
	}

	public Page<Cad_Projetos_Licenciamentos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_Licenciamentos find(Integer id) {
		Optional<Cad_Projetos_Licenciamentos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_Licenciamentos.class.getName(), null));
	}
	
	public Cad_Projetos_Licenciamentos insert(Cad_Projetos_Licenciamentos obj) {
		obj.setLicencaId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_Licenciamentos update(Cad_Projetos_Licenciamentos obj) {
		find(obj.getLicencaId());
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
