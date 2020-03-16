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

import com.prolagos.sispcbackend.domain.Cad_Projetos_Regulatorio;
import com.prolagos.sispcbackend.repositories.ProjRegulatorioRepository;
import com.prolagos.sispcbackend.repositories.ProjetosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjRegulatorioService {
	
	@Autowired
	private ProjRegulatorioRepository repo;
	
	@Autowired
	private ProjetosRepository repo2;
	
	public List<Cad_Projetos_Regulatorio> findAll() {
		return repo.findAll();
	}
	
	public List<Cad_Projetos_Regulatorio> findByProjeto(Integer projetoId) {
		return repo.regPorProjeto(projetoId);
	}

	public Page<Cad_Projetos_Regulatorio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_Regulatorio find(Integer id) {
		Optional<Cad_Projetos_Regulatorio> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_Regulatorio.class.getName(), null));
	}
	
	public Cad_Projetos_Regulatorio insert(Cad_Projetos_Regulatorio obj) {
		obj.setRegulatorioId(null);  //Utilizado em Entidade Com auto incremento
		obj.setProjetoId(repo2.findById(obj.getProjetoId().getProjetoId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!", null)));
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_Regulatorio update(Cad_Projetos_Regulatorio obj) {
		find(obj.getRegulatorioId());
		if(obj.getInicio() != null ){
			obj.setInicio(obj.getInicio().plusDays(1)) ;	
		}
		if(obj.getTermino() != null ){
			obj.setTermino(obj.getTermino().plusDays(1));	
		}
		
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
