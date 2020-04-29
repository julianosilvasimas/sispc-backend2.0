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

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;
import com.prolagos.sispcbackend.dto.EsgotoUnidadesDTO;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoUnidadesRepository;
import com.prolagos.sispcbackend.repositories.UsuarioRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ApprpaEsgotoUnidadesService {

	@Autowired
	private ApprpaEsgotoUnidadesRepository repo;

	
	public List<Apprpa_Esgoto_Unidades> findAll() {
		return repo.findAll();
	}

	
	public Page<Apprpa_Esgoto_Unidades> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Esgoto_Unidades find(Integer id) {
		Optional<Apprpa_Esgoto_Unidades> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Esgoto_Unidades.class.getName(), null));
	}

	@Autowired
	private UsuarioRepository repo2;
	
	public List<Apprpa_Esgoto_Unidades> findUsuario(String Usuario) {
		Cad_SisPC_Usuarios obj = this.repo2.findByEmail(Usuario);
		List<Apprpa_Esgoto_Unidades> lista = repo.findUsuario(obj);
		return lista;
	}
	
	public List<EsgotoUnidadesDTO> findOperadores() {
		List<EsgotoUnidadesDTO> lista = repo.findOperadores();
		return lista;
	}
	
	public Apprpa_Esgoto_Unidades insert(Apprpa_Esgoto_Unidades obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}

	public Apprpa_Esgoto_Unidades update(Apprpa_Esgoto_Unidades obj, Integer id) {
		obj.setId(id);
		obj = repo.save(obj);
		return obj;
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
