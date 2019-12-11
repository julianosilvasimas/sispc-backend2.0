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

import com.prolagos.sispcbackend.domain.Cad_SisPC_Empresas;
import com.prolagos.sispcbackend.repositories.EmpresasRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class EmpresasService {
	
	@Autowired
	private EmpresasRepository repo;

	public List<Cad_SisPC_Empresas> findAll() {
		return repo.findAll();
	}

	public Page<Cad_SisPC_Empresas> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_SisPC_Empresas find(Integer id) {
		Optional<Cad_SisPC_Empresas> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_SisPC_Empresas.class.getName(), null));
	}
	
	public Cad_SisPC_Empresas insert(Cad_SisPC_Empresas obj) {
		//obj.setEmpresaId(null);  //Utilizado em Entidade Com auto incremento
		return repo.save(obj);
	}
	
	public Cad_SisPC_Empresas update(Cad_SisPC_Empresas obj) {
		find(obj.getEmpresaId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Empresa que possui regionais e unidades");
		}
	}
	
}
